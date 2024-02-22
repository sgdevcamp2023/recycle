package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.member.MemberSourceDao;
import com.zzaug.member.entity.member.MemberEntity;
import com.zzaug.member.entity.member.MemberStatus;
import java.util.Optional;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 멤버 소스 DAO
 *
 * <p>요청한 멤버 ID에 대한 멤버 소스를 반환합니다.
 */
@Profile("usecase-test")
@TestComponent
public class UMockMemberSourceDao implements MemberSourceDao {

	public static final Long MEMBER_ID = 1L;

	@Override
	public Optional<MemberEntity> findByIdAndStatusAndDeletedFalse(Long id, MemberStatus status) {
		return Optional.of(MemberEntity.builder().id(id).status(status).build());
	}

	@Override
	public MemberEntity saveSource(MemberEntity memberEntity) {
		return memberEntity.toBuilder().id(MEMBER_ID).build();
	}
}
