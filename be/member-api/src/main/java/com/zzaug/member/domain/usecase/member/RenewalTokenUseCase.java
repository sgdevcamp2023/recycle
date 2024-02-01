package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.dto.member.RenewalTokenUseCaseRequest;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.auth.BlackTokenAuthDao;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.domain.support.entity.MemberContactExtractor;
import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
import com.zzaug.member.entity.auth.TokenData;
import com.zzaug.member.entity.auth.TokenType;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.ExternalContactEntity;
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
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RenewalTokenUseCase {

	private final BlackTokenAuthDao blackTokenAuthDao;
	private final AuthenticationDao authenticationDao;
	private final ExternalContactDao externalContactDao;

	private final MemberSourceQuery memberSourceQuery;

	private final TokenGenerator tokenGenerator;
	private final TokenResolver tokenResolver;

	@Transactional
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

		log.debug("Get member's contacts. memberId: {}", memberSource.getId());
		List<ExternalContactEntity> contacts =
				externalContactDao.findAllByMemberIdAndDeletedFalse(memberSource.getId());
		MemberContacts memberContacts = MemberContactExtractor.execute(contacts);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberSource.getId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		log.debug(
				"Save All used token to black list. accessToken: {}, refreshToken: {}",
				accessToken,
				refreshToken);
		saveAllUsedTokenToBlackList(accessToken, refreshToken);

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

	private void saveAllUsedTokenToBlackList(String accessToken, String refreshToken) {
		blackTokenAuthDao.saveAllBlackTokenAuth(
				List.of(
						BlackTokenAuthEntity.builder()
								.token(TokenData.builder().token(accessToken).build())
								.tokenType(TokenType.ACCESSTOKEN)
								.build(),
						BlackTokenAuthEntity.builder()
								.token(TokenData.builder().token(refreshToken).build())
								.tokenType(TokenType.REFRESHTOKEN)
								.build()));
	}
}
