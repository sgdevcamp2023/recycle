package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import java.util.Optional;

public interface MemberSourceDao {

	Optional<MemberEntity> findByIdAndStatusAndDeletedFalse(Long id, MemberStatus status);

	MemberEntity saveSource(MemberEntity memberEntity);
}
