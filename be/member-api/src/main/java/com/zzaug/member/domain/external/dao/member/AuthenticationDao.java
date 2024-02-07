package com.zzaug.member.domain.external.dao.member;

import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.CertificationData;

public interface AuthenticationDao {

	boolean existsByCertificationAndDeletedFalse(CertificationData certification);

	AuthenticationEntity saveAuthentication(AuthenticationEntity authenticationEntity);
}
