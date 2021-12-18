package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.Class;
import com.example.ElectronicGrade.model.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> list() {
        return classRepository.findAll();
    }
}
