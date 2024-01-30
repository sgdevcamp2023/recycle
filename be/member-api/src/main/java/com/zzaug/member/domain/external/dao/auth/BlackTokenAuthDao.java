package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.BlackTokenAuthEntity;

public interface BlackTokenAuthDao {

	BlackTokenAuthEntity saveBlackTokenAuth(BlackTokenAuthEntity blackTokenAuthEntity);
}
