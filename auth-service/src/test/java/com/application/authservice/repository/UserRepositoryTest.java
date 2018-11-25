package com.application.authservice.repository;

import com.application.authservice.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    User user = new User("Tom2","xxTomXX2","xxtom2@gmail.com","password2");

    @Before
    public void initialize() {
        when(userRepository.findByUsername("xxTomXX2")).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail("xxtom2@gmail.com")).thenReturn(true);
        when(userRepository.findByUsernameOrEmail("xxTomXX2","xxtom2@gmail.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("xxtom2@gmail.com")).thenReturn(Optional.of(user));
        when(userRepository.findByIdIn(Arrays.asList(user.getId()))).thenReturn(Arrays.asList(user));
    }

    @Test
    public void findByUsernameTest() {
        Optional<User> u = userRepository.findByUsername("xxTomXX2");
        Assert.assertEquals(u.get().getName(),user.getName());
        //assertThat(u).hasSize(1);
    }

    @Test
    public void existsByEmailTest() {
        Assert.assertTrue(userRepository.existsByEmail("xxtom2@gmail.com"));
    }

    @Test
    public void findByUsernameOrEmailTest() {
        Assert.assertNotNull(userRepository.findByUsernameOrEmail("xxTomXX2","xxtom2@gmail.com"));
    }

    @Test
    public void findByEmailTest() {
        Assert.assertNotNull(userRepository.findByEmail("xxtom2@gmail.com"));
    }

    @Test
    public void findByIdInTest() {
        Assert.assertNotNull(userRepository.findByIdIn(Arrays.asList(user.getId())));
    }


}
