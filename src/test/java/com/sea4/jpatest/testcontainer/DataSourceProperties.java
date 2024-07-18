package com.sea4.jpatest.testcontainer;

import static com.sea4.jpatest.testcontainer.AbstractContainerBaseTest.batchMysqlContainer;
import static com.sea4.jpatest.testcontainer.AbstractContainerBaseTest.myDataMysqlContainer;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.MySQLContainer;

@Slf4j
@TestConfiguration
@AutoConfigureTestDatabase
public class DataSourceProperties {

	@Primary
	@Bean("myDataDataSource")
	@Qualifier("myDataDataSource")
	public DataSource myDataDataSource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:" + myDataMysqlContainer.getFirstMappedPort() + "/root?allowMultiQueries=true");
		dataSource.setUsername(myDataMysqlContainer.getUsername());
		dataSource.setPassword(myDataMysqlContainer.getPassword());
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		return dataSource;
	}

	@Bean("batchDataSource")
	@Qualifier("batchDataSource")
	public DataSource batchDataSource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:" + batchMysqlContainer.getFirstMappedPort() + "/root?allowMultiQueries=true");
		dataSource.setUsername(batchMysqlContainer.getUsername());
		dataSource.setPassword(batchMysqlContainer.getPassword());
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		return dataSource;
	}


}
