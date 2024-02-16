package com.zzaug.security.event;

import com.zzaug.security.entity.log.InvalidTokenAccessEntity;
import com.zzaug.security.persistence.log.InvalidTokenAccessRepository;
import com.zzaug.security.persistence.transaction.SecurityTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvalidAccessTokenEventService {

	private final InvalidTokenAccessRepository invalidTokenAccessRepository;

	@SecurityTransactional
	public void execute(InvalidTokenAccessEvent event) {
		invalidTokenAccessRepository.save(
				InvalidTokenAccessEntity.builder()
						.token(event.getToken())
						.ip(event.getIp())
						.userAgent(event.getUserAgent())
						.build());
	}
}
