package com.zzaug.notification.domain.usecase;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"test", "usecase-test"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class AbstractUseCaseTest {}
