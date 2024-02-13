package com.zzaug.member.domain.usecase.config;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TestLocalDateTimeBaseEntity {

	@Builder.Default private LocalDateTime createAt = LocalDateTime.now();
	@Builder.Default private LocalDateTime updateAt = LocalDateTime.now();
}
