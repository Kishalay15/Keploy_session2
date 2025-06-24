package com.keploy.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EntityScan("com.keploy.demo.model")
@EnableJpaRepositories("com.keploy.demo.repository")
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
