package com.zzaug.security.persistence.transaction;

import com.zzaug.security.config.SecurityJpaDataSourceConfig;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public @interface SecurityTransactional {

	@AliasFor(annotation = Transactional.class, attribute = "value")
	String value() default "";

	@AliasFor(annotation = Transactional.class, attribute = "transactionManager")
	String transactionManager() default SecurityJpaDataSourceConfig.TRANSACTION_MANAGER_NAME;
}
