package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.Class;
import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        UserDetails user1 = userService.loadUserByUsername("dsikorowska");
        UserDetails user2 = userService.loadUserByUsername("inawrocka");
        Hibernate.initialize(((Student) user1).getStudentClass().getExtensions());
        Assert.assertTrue("Records unfetched", user1 != null);
        Assert.assertTrue("Records unfetched", user2 != null);
    }

}