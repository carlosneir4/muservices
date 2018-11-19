package com.application.authservice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.core.env.AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class AuthServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
