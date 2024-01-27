package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import com.zzaug.member.persistence.member.AuthenticationRepository;
import com.zzaug.member.persistence.member.ExternalContactRepository;
import com.zzaug.member.persistence.member.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!usecase-test")
@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {

	private final MemberRepository memberRepository;
	private final ExternalContactRepository externalContactRepository;
	private final AuthenticationRepository authenticationRepository;

	@Override
	public Optional<MemberEntity> findByIdAndStatusAndDeletedFalse(Long id, MemberStatus status) {
		return memberRepository.findByIdAndStatusAndDeletedFalse(id, status);
	}

	@Override
	public MemberEntity saveSource(MemberEntity memberEntity) {
		return memberRepository.save(memberEntity);
	}

	@Override
	public List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId) {
		return externalContactRepository.findAllByMemberIdAndDeletedFalse(memberId);
	}

	@Override
	public boolean existsByContactTypeAndSourceAndDeletedFalse(ContactType type, String source) {
		return externalContactRepository.existsByContactTypeAndSourceAndDeletedFalse(type, source);
	}

	@Override
	public boolean existsByCertificationAndDeletedFalse(CertificationData certification) {
		return authenticationRepository.existsByCertificationAndDeletedFalse(certification);
	}

	@Override
	public AuthenticationEntity saveAuthentication(AuthenticationEntity authenticationEntity) {
		return authenticationRepository.save(authenticationEntity);
	}

	@Override
	public ExternalContactEntity saveContact(ExternalContactEntity externalContactEntity) {
		return externalContactRepository.save(externalContactEntity);
	}
}
