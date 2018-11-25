package com.application.authservice.repository;

import com.application.authservice.AuthServiceApplication;
import com.application.authservice.model.Role;
import com.application.authservice.model.RoleName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_find_roles_repository() {
        Iterable<Role> role = roleRepository.findAll();
        assertThat(role).hasSize(2);
    }

    @Test
    public void findByNameAdminTest() {
        Assert.assertNotNull(roleRepository.findByName(RoleName.ROLE_ADMIN));
    }

    @Test
    public void findByNameUserTest() {
        Assert.assertNotNull(roleRepository.findByName(RoleName.ROLE_USER));
    }
}
