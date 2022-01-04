package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.LoginData;
import com.example.ElectronicGrade.model.repository.LoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginDataService {

    @Autowired
    private LoginDataRepository loginRepository;

    public List<LoginData> list() {
        return loginRepository.findAll();
    }
}
