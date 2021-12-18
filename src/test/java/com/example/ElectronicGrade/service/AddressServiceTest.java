package com.example.ElectronicGrade.service;

import com.example.ElectronicGrade.model.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Address> books = addressService.list();

        Assert.assertTrue("Records unfetched", books.size() > 0);
    }

}