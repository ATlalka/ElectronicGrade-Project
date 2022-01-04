package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import com.example.ElectronicGrade.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = studentRepository.findByUsername(username);
        if (user == null) {
            user = teacherRepository.findByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }

}
