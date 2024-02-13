package com.zzaug.member.persistence.member;

import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.MemberId;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {

	@DeletedFalse
	boolean existsByCertificationAndDeletedFalse(CertificationData certification);

	@DeletedFalse
	Optional<AuthenticationEntity> findByCertificationAndDeletedFalse(
			CertificationData certification);

	@MemberId
	@DeletedFalse
	Optional<AuthenticationEntity> findByMemberIdAndDeletedFalse(Long memberId);
}
