package com.application.authservice.controller;

import com.application.authservice.model.Role;
import com.application.authservice.model.RoleName;
import com.application.authservice.model.User;
import com.application.authservice.payload.request.LoginRequest;
import com.application.authservice.payload.request.SignUpRequest;
import com.application.authservice.repository.RoleRepository;
import com.application.authservice.repository.UserRepository;
import com.application.authservice.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.json.JacksonTester;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    MockMvc mockMvc;

    @Mock
    AuthenticationManager authenticationManagerMock;

    @Mock
    JwtTokenProvider tokenProviderMock;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;


    private JacksonTester<LoginRequest> loginJackson;

    private JacksonTester<SignUpRequest> signUPJackson;

    @InjectMocks
    AuthController authController;

    @Mock
    private Authentication authentication;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
    }

    @Test
    public void sigInWithRightUserTest() throws Exception {
        String jwt = "token";
        LoginRequest login = new LoginRequest();
        login.setPassword("Foo");
        login.setUsernameOrEmail("Foo");
        given(authenticationManagerMock.authenticate(new UsernamePasswordAuthenticationToken(
                "Foo","Foo"))).willReturn( authentication);
        given(tokenProviderMock.generateToken(authentication)).willReturn(jwt);
        mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(loginJackson.write(login).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.accessToken").value(jwt));

    }

    @Test
    public void sigInBadRequestTest() throws Exception {
        LoginRequest login = new LoginRequest();
        login.setPassword(null);
        login.setUsernameOrEmail(null);
        mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(loginJackson.write(login).getJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void signUpUserTest() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setPassword("password");
        signUpRequest.setName("Name");
        signUpRequest.setEmail("email@a.com");
        signUpRequest.setUsername("userName");
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        given(userRepository.existsByUsername(signUpRequest.getUsername())).willReturn(false);
        given(userRepository.existsByEmail(signUpRequest.getEmail())).willReturn(false);
        given(roleRepository.findByName(RoleName.ROLE_USER)).willReturn(java.util.Optional.of(new Role()));
        given(userRepository.save(any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(signUPJackson.write(signUpRequest).getJson()))
                .andExpect(status().isCreated());
    }


}
