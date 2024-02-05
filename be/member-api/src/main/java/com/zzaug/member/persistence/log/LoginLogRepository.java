package com.zzaug.member.persistence.log;

import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.MemberId;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, Long> {

	@MemberId
	@DeletedFalse
	Optional<LoginLogEntity> findTopByMemberIdAndStatusAndDeletedFalse(
			Long memberId, LoginStatus status);
}
