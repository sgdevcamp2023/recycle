package com.zzaug.member.persistence.member;

import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.persistence.support.checker.DeletedFalse;
import com.zzaug.member.persistence.support.checker.MemberId;
import com.zzaug.member.persistence.support.checker.ZzuagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@ZzuagRepository
public interface ExternalContactRepository extends JpaRepository<ExternalContactEntity, Long> {

	@DeletedFalse
	boolean existsByContactTypeAndSourceAndDeletedFalse(ContactType type, String source);

	@MemberId
	@DeletedFalse
	Optional<ExternalContactEntity> findByMemberIdAndContactTypeAndDeletedFalse(
			Long memberId, ContactType type);

	@MemberId
	@DeletedFalse
	List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId);
}
