package com.zzaug.member.config;

import com.zzaug.flyway.datasource.EntityDataSource;
import com.zzaug.security.config.SecurityJpaDataSourceConfig;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = MemberAppConfig.BASE_PACKAGE,
		transactionManagerRef = MemberJpaDataSourceConfig.TRANSACTION_MANAGER_NAME,
		entityManagerFactoryRef = MemberJpaDataSourceConfig.ENTITY_MANAGER_FACTORY_NAME)
public class MemberJpaDataSourceConfig {

	private static final String ENTITY_PROPERTY_PREFIX = MemberAppConfig.PROPERTY_PREFIX + ".entity";

	public static final String ENTITY_BEAN_NAME_PREFIX = MemberAppConfig.BEAN_NAME_PREFIX + "Entity";
	public static final String ENTITY_MANAGER_FACTORY_NAME =
			ENTITY_BEAN_NAME_PREFIX + "ManagerFactory";
	public static final String TRANSACTION_MANAGER_NAME =
			ENTITY_BEAN_NAME_PREFIX + "TransactionManager";
	public static final String MEMBER_SECURITY_TRANSACTION_MANAGER_NAME =
			"MemberSecurityTransactionManager";
	public static final String DATASOURCE_NAME = ENTITY_BEAN_NAME_PREFIX + "DataSource";
	private static final String JPA_PROPERTIES_NAME = ENTITY_BEAN_NAME_PREFIX + "JpaProperties";
	private static final String HIBERNATE_PROPERTIES_NAME =
			ENTITY_BEAN_NAME_PREFIX + "HibernateProperties";
	private static final String JPA_VENDOR_ADAPTER_NAME =
			ENTITY_BEAN_NAME_PREFIX + "JpaVendorAdapter";
	private static final String PERSIST_UNIT = ENTITY_BEAN_NAME_PREFIX + "PersistenceUnit";
	private static final String ENTITY_MANAGER_FACTORY_BUILDER_NAME =
			ENTITY_BEAN_NAME_PREFIX + "ManagerFactoryBuilder";

	@Bean(name = DATASOURCE_NAME)
	@ConfigurationProperties(prefix = ENTITY_PROPERTY_PREFIX + ".datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public EntityDataSource entityDataSource() {
		return new EntityDataSource(dataSource());
	}

	@Bean(name = JPA_PROPERTIES_NAME)
	@ConfigurationProperties(prefix = ENTITY_PROPERTY_PREFIX + ".jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Bean(name = HIBERNATE_PROPERTIES_NAME)
	@ConfigurationProperties(prefix = ENTITY_PROPERTY_PREFIX + ".jpa.hibernate")
	public HibernateProperties hibernateProperties() {
		return new HibernateProperties();
	}

	@Bean(name = JPA_VENDOR_ADAPTER_NAME)
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean(name = ENTITY_MANAGER_FACTORY_BUILDER_NAME)
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
			@Qualifier(value = JPA_VENDOR_ADAPTER_NAME) JpaVendorAdapter jpaVendorAdapter,
			@Qualifier(value = JPA_PROPERTIES_NAME) JpaProperties jpaProperties,
			ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {

		Map<String, String> jpaPropertyMap = jpaProperties.getProperties();
		return new EntityManagerFactoryBuilder(
				jpaVendorAdapter, jpaPropertyMap, persistenceUnitManager.getIfAvailable());
	}

	@Bean(name = ENTITY_MANAGER_FACTORY_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier(value = DATASOURCE_NAME) DataSource dataSource,
			@Qualifier(value = ENTITY_MANAGER_FACTORY_BUILDER_NAME) EntityManagerFactoryBuilder builder) {
		Map<String, String> jpaPropertyMap = jpaProperties().getProperties();
		Map<String, Object> hibernatePropertyMap =
				hibernateProperties().determineHibernateProperties(jpaPropertyMap, new HibernateSettings());
		return builder
				.dataSource(dataSource)
				.properties(hibernatePropertyMap)
				.persistenceUnit(PERSIST_UNIT)
				.packages(MemberAppConfig.BASE_PACKAGE)
				.build();
	}

	@Bean(name = TRANSACTION_MANAGER_NAME)
	public PlatformTransactionManager transactionManager(
			@Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Profile("!repository-test")
	@Bean(name = MEMBER_SECURITY_TRANSACTION_MANAGER_NAME)
	public PlatformTransactionManager chainedTransactionManager(
			@Qualifier(TRANSACTION_MANAGER_NAME) PlatformTransactionManager emf,
			@Qualifier(SecurityJpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
					PlatformTransactionManager semf) {
		return new ChainedTransactionManager(semf, emf);
	}
}
