package com.zzaug.member.domain.support.usecase;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!integration-test")
@Component
public class RandomAuthCodeGeneratorImpl implements RandomAuthCodeGenerator {

	@Override
	public String generate(int count) {
		return RandomStringUtils.random(count, true, true);
	}
}
