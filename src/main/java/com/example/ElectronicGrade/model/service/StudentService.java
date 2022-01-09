package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public List<Student> findByClassId(Long idClass) {
        return studentRepository.findByClassId(idClass);
    }

}
