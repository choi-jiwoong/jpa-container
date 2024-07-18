package com.sea4.jpatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(scanBasePackages = "com.sea4.jpatest")
@TestConfiguration
@EnableJpaAuditing
class JpaTestApplicationTests {


	@Test
	void contextLoads() {

	}

}
