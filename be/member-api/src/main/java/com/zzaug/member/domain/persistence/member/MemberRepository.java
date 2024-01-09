package com.zzaug.member.domain.persistence.member;

import com.zzaug.member.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {}
