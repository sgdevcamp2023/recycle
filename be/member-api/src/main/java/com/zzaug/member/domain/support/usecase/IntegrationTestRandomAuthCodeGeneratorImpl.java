package com.zzaug.member.domain.support.usecase;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("integration-test")
@Component
public class IntegrationTestRandomAuthCodeGeneratorImpl implements RandomAuthCodeGenerator {

	@Override
	public String generate(int count) {
		return "IfMr6pB";
	}
}
