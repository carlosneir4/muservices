package com.application.authservice.repository;

import com.application.authservice.AuthServiceApplication;
import com.application.authservice.model.RoleName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByNameAdminTest() {
        Assert.assertNotNull(roleRepository.findByName(RoleName.ROLE_ADMIN));
    }

    @Test
    public void findByNameUserTest() {
        Assert.assertNotNull(roleRepository.findByName(RoleName.ROLE_USER));
    }
}
