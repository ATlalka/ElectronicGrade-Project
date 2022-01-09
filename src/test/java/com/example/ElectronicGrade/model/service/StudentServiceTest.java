package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.Grade;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Student> students = studentService.list();
        List<Map<Subject, List<Grade>>> maps = students.stream().map(Student::getGradesMap).collect(Collectors.toList());
        Assert.assertTrue("Records unfetched", students.size() > 0);
    }

    @Test
    public void studentClass() {
        List<Student> students = studentService.findByClassId(1L);
        students.get(0);
    }
}