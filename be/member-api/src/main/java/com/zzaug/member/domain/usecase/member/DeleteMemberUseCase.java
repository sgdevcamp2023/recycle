package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.DeleteMemberUseCaseRequest;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.member.MemberSourceDao;
import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteMemberUseCase {

	private final MemberSourceDao memberSourceDao;

	@Transactional
	public void execute(DeleteMemberUseCaseRequest request) {
		final Long memberId = request.getMemberId();

		log.debug("Get member. memberId: {}", memberId);
		Optional<MemberEntity> memberSource =
				memberSourceDao.findByIdAndStatusAndDeletedFalse(memberId, MemberStatus.REGULAR);
		if (memberSource.isEmpty()) {
			throw new IllegalArgumentException("존재하지 않는 회원입니다.");
		}

		MemberEntity memberEntity = memberSource.get();
		MemberEntity withdrawnMember = memberEntity.toBuilder().status(MemberStatus.WITHDRAWN).build();
		log.debug("Save member status to withdrawn. memberId: {}", memberId);
		memberSourceDao.saveSource(withdrawnMember);
	}
}
