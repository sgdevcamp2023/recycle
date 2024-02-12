package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.List;

public interface ExternalContactDao {

	boolean existsByContactTypeAndSourceAndDeletedFalse(ContactType type, String source);

	List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId);
}
