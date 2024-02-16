package com.zzaug.member.persistence.member;

import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	@DeletedFalse
	Optional<MemberEntity> findByIdAndStatusAndDeletedFalse(Long id, MemberStatus status);
}
