package com.zzaug.member.domain.external.dao.log;

import com.zzaug.member.entity.log.LoginLogEntity;
import com.zzaug.member.entity.log.LoginStatus;
import com.zzaug.member.persistence.log.LoginLogRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!usecase-test")
@Repository
@RequiredArgsConstructor
public class LoginLogDaoImpl implements LoginLogDao {

	private final LoginLogRepository loginLogRepository;

	@Override
	public LoginLogEntity saveLoginLog(LoginLogEntity entity) {
		return loginLogRepository.save(entity);
	}

	@Override
	public Optional<LoginLogEntity> findTopByMemberIdAndStatusAndDeletedFalse(
			Long memberId, LoginStatus status) {
		return loginLogRepository.findTopByMemberIdAndStatusAndDeletedFalse(memberId, status);
	}
}
