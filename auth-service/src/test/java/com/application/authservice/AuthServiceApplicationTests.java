package com.application.authservice;

import com.application.authservice.payload.request.LoginRequest;
import com.application.authservice.payload.request.SignUpRequest;
import com.application.authservice.payload.response.ApiResponse;
import com.application.authservice.payload.response.JwtAuthenticationResponse;
import com.application.authservice.repository.UserRepository;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.core.env.AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class AuthServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	/*
	@Test
	public void shouldNotSingInTest() {
		LoginRequest login = new LoginRequest();
		login.setPassword("Foo");
		login.setUsernameOrEmail("Foo");
		ResponseEntity<JwtAuthenticationResponse> responseEntity =
				restTemplate.postForEntity("/api/auth/signin", login, JwtAuthenticationResponse.class);
		responseEntity.getBody();
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
		//assertEquals("Foo", result.getAccessToken());
	}

	@Test
	public void shouldSingUpTest() {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setPassword("password");
		signUpRequest.setName("Name");
		signUpRequest.setEmail("email@a.com");
		signUpRequest.setUsername("userName");
		ResponseEntity<ApiResponse> responseEntity =
				restTemplate.postForEntity("/api/auth/signup", signUpRequest, ApiResponse.class);
		responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void shouldSingInTest() {
		LoginRequest login = new LoginRequest();
		login.setPassword("password");
		login.setUsernameOrEmail("userName");
		ResponseEntity<JwtAuthenticationResponse> responseEntity =
				restTemplate.postForEntity("/api/auth/signin", login, JwtAuthenticationResponse.class);
		responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}*/

}
