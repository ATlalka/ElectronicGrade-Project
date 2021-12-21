package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.Class;
import com.example.ElectronicGrade.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Student> students = studentService.list();
        Assert.assertTrue("Records unfetched", students.size() > 0);
    }

}