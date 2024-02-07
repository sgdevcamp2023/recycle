package com.zzaug.member.domain.external.dao.auth;

import com.zzaug.member.entity.auth.BlackTokenAuthEntity;
import java.util.List;

public interface BlackTokenAuthDao {

	BlackTokenAuthEntity saveBlackTokenAuth(BlackTokenAuthEntity blackTokenAuthEntity);

	List<BlackTokenAuthEntity> saveAllBlackTokenAuth(
			List<BlackTokenAuthEntity> blackTokenAuthEntities);
}
