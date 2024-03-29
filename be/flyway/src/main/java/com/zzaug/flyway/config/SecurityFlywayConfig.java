package com.zzaug.flyway.config;

import static com.zzaug.flyway.FlywayConfig.BEAN_NAME_PREFIX;
import static com.zzaug.flyway.FlywayConfig.PROPERTY_PREFIX;

import com.zzaug.flyway.datasource.SecurityDataSource;
import com.zzaug.flyway.datasource.support.DataSourceFilter;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class SecurityFlywayConfig {

	// base property prefix for flyway
	public static final String BASE_PROPERTY_PREFIX = PROPERTY_PREFIX + ".security";
	private static final String BASE_BEAN_NAME_PREFIX = BEAN_NAME_PREFIX + "Security";

	// bean name for flyway configuration
	private static final String FLYWAY = BASE_BEAN_NAME_PREFIX;
	private static final String FLYWAY_PROPERTIES = BASE_BEAN_NAME_PREFIX + "Properties";
	private static final String FLYWAY_MIGRATION_INITIALIZER =
			BASE_BEAN_NAME_PREFIX + "MigrationInitializer";
	private static final String FLYWAY_VALIDATE_INITIALIZER =
			BASE_BEAN_NAME_PREFIX + "ValidateInitializer";
	private static final String FLYWAY_CONFIGURATION = BASE_BEAN_NAME_PREFIX + "Configuration";
	private static final String FLYWAY_DATASOURCE = BASE_BEAN_NAME_PREFIX + "DataSource";

	private final DataSourceFilter dataSourceFilter;

	@Bean(name = FLYWAY)
	public Flyway flyway(
			@Qualifier(FLYWAY_CONFIGURATION)
					org.flywaydb.core.api.configuration.Configuration configuration) {
		return new Flyway(configuration);
	}

	@Profile({"!local && !new"})
	@Bean(name = FLYWAY_VALIDATE_INITIALIZER)
	public FlywayMigrationInitializer flywayValidateInitializer(
			@Qualifier(value = FLYWAY) Flyway flyway) {
		return new FlywayMigrationInitializer(flyway, Flyway::validate);
	}

	@Profile({"!local"})
	@Bean(name = FLYWAY_MIGRATION_INITIALIZER)
	public FlywayMigrationInitializer flywayMigrationInitializer(
			@Qualifier(value = FLYWAY) Flyway flyway) {
		return new FlywayMigrationInitializer(flyway, Flyway::migrate);
	}

	@Bean(name = FLYWAY_PROPERTIES)
	@ConfigurationProperties(prefix = BASE_PROPERTY_PREFIX)
	public FlywayProperties flywayProperties() {
		return new FlywayProperties();
	}

	@Bean(name = FLYWAY_CONFIGURATION)
	public org.flywaydb.core.api.configuration.Configuration configuration(
			List<DataSource> dataSources) {
		FluentConfiguration configuration = new FluentConfiguration();
		configuration.dataSource(dataSourceFilter.filter(SecurityDataSource.class, dataSources));
		flywayProperties().getLocations().forEach(configuration::locations);
		return configuration;
	}
}
