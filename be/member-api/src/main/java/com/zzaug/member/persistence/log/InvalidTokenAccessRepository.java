package com.zzaug.member.persistence.log;

import com.zzaug.member.entity.log.InvalidTokenAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenAccessRepository
		extends JpaRepository<InvalidTokenAccessEntity, Long> {}
