package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.Form;
import com.example.ElectronicGrade.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormServiceTest {

    @Autowired
    private FormService formService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Form> forms = formService.list();
        List<Student> students = forms.get(0).getStudents();
        Assert.assertTrue("Records unfetched", forms.size() > 0);
    }

}