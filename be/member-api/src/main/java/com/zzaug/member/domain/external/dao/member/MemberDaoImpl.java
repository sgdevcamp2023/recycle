package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
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

	@Override
	public Optional<MemberEntity> findByIdAndStatusAndDeletedFalse(Long id, MemberStatus status) {
		return memberRepository.findByIdAndStatusAndDeletedFalse(id, status);
	}

	@Override
	public List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId) {
		return externalContactRepository.findAllByMemberIdAndDeletedFalse(memberId);
	}
}
