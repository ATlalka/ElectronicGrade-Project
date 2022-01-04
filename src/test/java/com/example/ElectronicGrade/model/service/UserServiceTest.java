package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        UserDetails user1 = userService.loadUserByUsername("dsikorowska");
        UserDetails user2 = userService.loadUserByUsername("inawrocka");
        Assert.assertTrue("Records unfetched", user1 != null);
        Assert.assertTrue("Records unfetched", user2 != null);
    }

}