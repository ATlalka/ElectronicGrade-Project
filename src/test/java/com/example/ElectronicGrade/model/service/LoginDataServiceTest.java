package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.LoginData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDataServiceTest {

    @Autowired
    private LoginDataService loginDataService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<LoginData> logins = loginDataService.list();
        Assert.assertTrue("Records unfetched", logins.size() > 0);
    }

}