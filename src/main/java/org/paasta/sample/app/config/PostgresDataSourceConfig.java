package org.paasta.sample.app.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.paasta.sample.app.postgresql", entityManagerFactoryRef = "postgresqlEntityManager", transactionManagerRef = "postgresqlTransactionManager")
public class PostgresDataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "postgresql.datasource")
	public DataSource postgresqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	/*
	 * EntityManager
	 */
	@Primary
	@Bean(name = "postgresqlEntityManager")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("show-sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create");
		return builder
					.dataSource(postgresqlDataSource())
					.packages("org.paasta.sample.app.entity.postgresql")
					.persistenceUnit("postgresql")
					.properties(properties)
					.build();
	}

	/*
	 * transactionFactory
	 */
	@Primary
	@Bean(name = "postgresqlTransactionManager")
	public PlatformTransactionManager postgresqlTransactionManager(
			@Qualifier("postgresqlEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
