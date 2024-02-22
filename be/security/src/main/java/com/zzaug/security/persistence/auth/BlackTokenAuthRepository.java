package com.zzaug.security.persistence.auth;

import com.zzaug.security.entity.auth.BlackTokenAuthEntity;
import com.zzaug.security.entity.auth.TokenData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackTokenAuthRepository extends JpaRepository<BlackTokenAuthEntity, Long> {

	boolean existsByTokenAndDeletedFalse(TokenData token);
}
