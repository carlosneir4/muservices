package com.application.authservice.security;

import com.application.authservice.AuthServiceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@TestPropertySource(locations="classpath:application-test.properties",
        properties = {"app.jwtSecret= JWTSuperSecretKey", "app.jwtExpirationInMs = 604800000"})
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @Test
    public void generateTokenTest(){
        UserPrincipal principal = new UserPrincipal(0l,"Foo","Foo","Foo@mail.com","Foo",
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        when(authentication.getPrincipal()).thenReturn(principal);
        System.out.print(jwtTokenProvider.generateToken(authentication));
        Assert.assertNotNull(jwtTokenProvider.generateToken(authentication));
    }

    @Test
    public void getUserIdFromJWTTest(){
        //Token is generated in generateTokenTest method
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwIiwiaWF0IjoxNTQyNTg5MjAwLCJleHAiOjE1NDMxOTQwMDB9.z5DSPoLoXGMSI1C" +
                "alYae_JGoh1LuqVyVAvoTS346Fnyn1NRNuuXl4vkfvmueoN6XgrsNEdsCYeq_d2tFj9gD3g";

        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        Assert.assertEquals(java.util.Optional.of(0l),java.util.Optional.of(userId));
    }

    @Test
    public void validateTokenTest(){
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwIiwiaWF0IjoxNTQyNTg5MjAwLCJleHAiOjE1NDMxOTQwMDB9.z5DSPoLoXGMSI1C" +
                "alYae_JGoh1LuqVyVAvoTS346Fnyn1NRNuuXl4vkfvmueoN6XgrsNEdsCYeq_d2tFj9gD3g";
        Assert.assertTrue(jwtTokenProvider.validateToken(token));

    }

    @Test(expected = AssertionError.class)
    public void validateEmptyTokenTest() throws Exception {
        Assert.assertTrue(jwtTokenProvider.validateToken(""));
    }


}
