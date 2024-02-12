package com.zzaug.member.persistence.support.transaction;

import com.zzaug.member.config.MemberJpaDataSourceConfig;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public @interface UseCaseTransactional {

	@AliasFor(annotation = Transactional.class, attribute = "value")
	String value() default "";

	@AliasFor(annotation = Transactional.class, attribute = "transactionManager")
	String transactionManager() default MemberJpaDataSourceConfig.TRANSACTION_MANAGER_NAME;

	@AliasFor(annotation = Transactional.class, attribute = "readOnly")
	boolean readOnly() default false;
}
