package com.zzaug.security.persistence.log;

import com.zzaug.security.entity.log.InvalidTokenAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenAccessRepository
		extends JpaRepository<InvalidTokenAccessEntity, Long> {}
