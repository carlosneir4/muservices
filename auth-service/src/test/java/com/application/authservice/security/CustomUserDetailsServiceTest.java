package com.application.authservice.security;

import com.application.authservice.AuthServiceApplication;

import com.application.authservice.model.User;
import com.application.authservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private UserRepository userRepositoryMock;

    User dummyUser = new User("Foo","Foo","Foo","Foo");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void loadUserByUsernameTest() {
        when(userRepositoryMock.findByUsernameOrEmail(eq("Foo"),eq("Foo"))).thenReturn(Optional.ofNullable(dummyUser));
        doAnswer(returnsFirstArg()).when(userRepositoryMock).save(any(User.class));
        UserDetails result = customUserDetailsService.loadUserByUsername("Foo");
        assertEquals("Foo", result.getUsername());
        assertNotNull(result.getUsername());
        verify(userRepositoryMock).findByUsernameOrEmail("Foo", "Foo");
    }


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByEmptyUsernameTest() throws Exception {
        customUserDetailsService.loadUserByUsername("");
    }

    @Test
    public void loadUserByIdTest(){
        when(userRepositoryMock.findById(eq(0l))).thenReturn(Optional.ofNullable(dummyUser));
        doAnswer(returnsFirstArg()).when(userRepositoryMock).save(any(User.class));
        UserDetails result = customUserDetailsService.loadUserById(0l);
        assertEquals("Foo", result.getUsername());
        assertNotNull(result.getUsername());
        verify(userRepositoryMock).findById(0l);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByEmptyIdTest(){
        customUserDetailsService.loadUserById(null);
    }
}
