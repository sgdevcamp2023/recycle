package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.dto.member.RenewalTokenUseCaseRequest;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.security.auth.BlackTokenAuthCommand;
import com.zzaug.member.domain.external.security.auth.EnrollTokenCacheService;
import com.zzaug.member.domain.external.security.auth.ReplaceTokenCacheService;
import com.zzaug.member.domain.external.service.member.MemberContactsQuery;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.token.AuthToken;
import com.zzaug.security.token.TokenGenerator;
import com.zzaug.security.token.TokenResolver;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RenewalTokenUseCase {

	private final AuthenticationDao authenticationDao;

	private final MemberSourceQuery memberSourceQuery;
	private final MemberContactsQuery memberContactsQuery;

	// security
	private final TokenGenerator tokenGenerator;
	private final TokenResolver tokenResolver;
	private final BlackTokenAuthCommand blackTokenAuthCommand;
	private final EnrollTokenCacheService enrollBlackTokenCacheServiceImpl;
	private final ReplaceTokenCacheService replaceWhiteTokenCacheServiceImpl;

	@UseCaseTransactional
	public MemberAuthToken execute(RenewalTokenUseCaseRequest request) {
		final String refreshToken = request.getRefreshToken();
		final String accessToken = request.getAccessToken();
		final Long memberId = resolveMemberId(refreshToken);

		log.debug("Get member source. memberId: {}", memberId);
		MemberSource memberSource = memberSourceQuery.execute(memberId);

		log.debug("Get member authentication. memberId: {}", memberId);
		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByMemberIdAndDeletedFalse(memberId);
		if (authenticationSource.isEmpty()) {
			throw new SourceNotFoundException(DBSource.AUTHENTICATION, "MemberId", memberId);
		}
		MemberAuthentication memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		MemberContacts memberContacts = memberContactsQuery.execute(memberAuthentication);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberSource.getId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		blackTokenAuthCommand.execute(accessToken, refreshToken);
		enrollBlackTokenCacheServiceImpl.execute(accessToken, refreshToken);
		replaceWhiteTokenCacheServiceImpl.execute(accessToken, authToken.getAccessToken());

		return MemberAuthToken.builder()
				.accessToken(authToken.getAccessToken())
				.refreshToken(authToken.getRefreshToken())
				.build();
	}

	@NotNull
	private Long resolveMemberId(String refreshToken) {
		Optional<Long> idSource = tokenResolver.resolveId(refreshToken);
		if (idSource.isEmpty()) {
			throw new IllegalStateException("MemberId is not found in refreshToken.");
		}
		return idSource.get();
	}
}
