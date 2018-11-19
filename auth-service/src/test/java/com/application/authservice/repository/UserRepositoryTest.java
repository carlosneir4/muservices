package com.application.authservice.repository;

import com.application.authservice.AuthServiceApplication;
import com.application.authservice.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User user = new User("Tom2","xxTomXX2","xxtom2@gmail.com","password2");

    @Before
    public void initialize() {
        userRepository.save(user);
    }

    @Test
    public void findByUsernameTest() {
        Assert.assertNotNull(userRepository.findByUsername("xxTomXX2"));
    }

    @Test
    public void existsByEmailTest() {
        Assert.assertNotNull(userRepository.existsByEmail("xxtom2@gmail.com"));
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

    @After
    public void reset (){
        userRepository.delete(user);
    }

}
