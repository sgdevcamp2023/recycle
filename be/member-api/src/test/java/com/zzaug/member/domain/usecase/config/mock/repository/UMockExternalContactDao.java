package com.zzaug.member.domain.usecase.config.mock.repository;

import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.usecase.config.TestLocalDateTimeBaseEntity;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

/**
 * 테스트용 외부 연락처 DAO
 *
 * <p>요청한 멤버 ID에 대한 외부 연락처를 반환합니다.
 *
 * <p>현재는 이메일과 깃허브만 지원합니다.
 *
 * <p>이메일: sample@email.com
 *
 * <p>깃허브: git
 *
 * <p>프로파일이 exist-external-contact인 경우 이미 외부 연락처가 존재합니다.
 *
 * <p>프로파일이 exist-external-contact가 아닌 경우 이미 외부 연락처가 존재하지 않습니다.
 */
@Profile("usecase-test")
@TestComponent
public class UMockExternalContactDao implements ExternalContactDao, ApplicationContextAware {

	public static final String EMAIL = "sampel@emai.com";
	public static final String GITHUB = "git";

	private List<String> activeProfiles;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		activeProfiles = List.of(applicationContext.getEnvironment().getActiveProfiles());
	}

	@Override
	public List<ExternalContactEntity> findAllByMemberIdAndDeletedFalse(Long memberId) {
		List<ExternalContactEntity> sources = new ArrayList<>();
		TestLocalDateTimeBaseEntity time = TestLocalDateTimeBaseEntity.builder().build();
		ExternalContactEntity email =
				ExternalContactEntity.builder()
						.id(1L)
						.memberId(memberId)
						.contactType(ContactType.EMAIL)
						.source(EMAIL)
						.createAt(time.getCreateAt())
						.updateAt(time.getUpdateAt())
						.build();
		ExternalContactEntity github =
				ExternalContactEntity.builder()
						.id(2L)
						.memberId(memberId)
						.contactType(ContactType.GITHUB)
						.source(GITHUB)
						.createAt(time.getCreateAt())
						.updateAt(time.getUpdateAt())
						.build();
		sources.add(email);
		sources.add(github);
		return sources;
	}

	@Override
	public boolean existsByContactTypeAndSourceAndDeletedFalse(ContactType type, String source) {
		if (activeProfiles.contains("exist-contact")) {
			return true;
		}
		return false;
	}
}
