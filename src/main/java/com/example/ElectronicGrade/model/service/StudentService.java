package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Student> findById(Long idStudent) {
        return studentRepository.findById(idStudent);
    }
}
