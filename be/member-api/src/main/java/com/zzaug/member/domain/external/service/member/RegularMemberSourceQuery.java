package com.zzaug.member.domain.external.service.member;

import com.zzaug.member.domain.external.dao.member.MemberSourceDao;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("!usecase-test")
@Slf4j
@Service
@RequiredArgsConstructor
public class RegularMemberSourceQuery implements MemberSourceQuery {

	private final MemberSourceDao memberRepository;

	@Transactional(readOnly = true)
	public MemberSource execute(Long memberId) {
		Optional<MemberEntity> entity =
				memberRepository.findByIdAndStatusAndDeletedFalse(memberId, MemberStatus.REGULAR);
		if (entity.isEmpty()) {
			log.warn("Member not found. memberId: {}", memberId);
			throw new IllegalArgumentException("Member not found.");
		}
		MemberEntity source = entity.get();
		return new MemberSource(source.getId());
	}
}
