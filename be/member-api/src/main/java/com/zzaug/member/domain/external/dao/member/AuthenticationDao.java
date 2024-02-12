package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;
import java.util.Optional;

public interface AuthenticationDao {

	boolean existsByCertificationAndDeletedFalse(CertificationData certification);

	Optional<AuthenticationEntity> findByMemberIdAndDeletedFalse(Long memberId);

	AuthenticationEntity saveAuthentication(AuthenticationEntity authenticationEntity);
}
