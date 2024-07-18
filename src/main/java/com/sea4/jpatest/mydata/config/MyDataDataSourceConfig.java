package com.sea4.jpatest.mydata.config;


import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
	basePackages = MyDataDataSourceConfig.PACKAGE_PATH,
	entityManagerFactoryRef = "myDataEntityManagerFactory",
	transactionManagerRef = "myDataTransactionManager"
)
public class MyDataDataSourceConfig {

	public static final String PACKAGE_PATH = "com.sea4.jpatest.mydata";

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.mydata-datasource")
	public DataSource myDataDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean myDataEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(myDataDataSource());
		em.setPackagesToScan(MyDataDataSourceConfig.PACKAGE_PATH);
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	@Primary
	@Bean
	public PlatformTransactionManager myDataTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(myDataEntityManagerFactory().getObject());
		return transactionManager;
	}
}
