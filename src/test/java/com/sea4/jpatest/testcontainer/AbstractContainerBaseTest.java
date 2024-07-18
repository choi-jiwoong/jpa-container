package com.sea4.jpatest.testcontainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Slf4j
public abstract class AbstractContainerBaseTest {

	@Container
	@ServiceConnection
	public static MySQLContainer<?> myDataMysqlContainer = new MySQLContainer<>("mysql:8.0")
			.withDatabaseName("MY_DATA")
			.withUsername("root")
			.withPassword("password")
			.withExposedPorts(3306);

	@Container
	@ServiceConnection
	public static MySQLContainer<?> batchMysqlContainer = new MySQLContainer<>("mysql:8.0")
		.withDatabaseName("BATCH")
		.withUsername("root")
		.withPassword("password")
		.withExposedPorts(3306);


	static {
		myDataMysqlContainer.start();
		batchMysqlContainer.start();
		log.info("AbstractContainerBaseTest");
	}
}
