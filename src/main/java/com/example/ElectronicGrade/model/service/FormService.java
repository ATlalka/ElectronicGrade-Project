package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.Form;
import com.example.ElectronicGrade.model.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public List<Form> list() {
        return formRepository.findAll();
    }
}
