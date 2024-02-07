package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.List;

public interface ExternalContactDao {

	List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId);
}
