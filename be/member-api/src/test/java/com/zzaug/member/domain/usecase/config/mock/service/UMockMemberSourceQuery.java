package com.zzaug.member.domain.usecase.config.mock.service;

import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.member.MemberSource;
import org.springframework.boot.test.context.TestComponent;

/**
 * 테스트용 멤버 소스 쿼리
 *
 * <p>요청한 멤버 ID에 대한 멤버 소스를 반환합니다.
 */
@TestComponent
public class UMockMemberSourceQuery implements MemberSourceQuery {

	@Override
	public MemberSource execute(Long memberId) {
		return MemberSource.builder().id(memberId).build();
	}
}
