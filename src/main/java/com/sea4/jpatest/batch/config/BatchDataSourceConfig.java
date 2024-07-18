package com.sea4.jpatest.batch.config;


import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
	basePackages = BatchDataSourceConfig.PACKAGE_PATH,
	entityManagerFactoryRef = "batchEntityManagerFactory",
	transactionManagerRef = "batchTransactionManager"
)
public class BatchDataSourceConfig {

	public static final String PACKAGE_PATH = "com.sea4.jpatest.batch";

	@Bean
	@ConfigurationProperties(prefix = "spring.batch-datasource")
	public DataSource batchDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(batchDataSource());
		em.setPackagesToScan(BatchDataSourceConfig.PACKAGE_PATH);
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	@Bean
	public PlatformTransactionManager batchTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(batchEntityManagerFactory().getObject());
		return transactionManager;
	}
}
