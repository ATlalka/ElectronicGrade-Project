package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import com.example.ElectronicGrade.model.repository.TeacherRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = studentRepository.findByUsername(username);
        if (user != null) {
            Hibernate.initialize(((Student) user).getStudentClass().getExtensions());
            Hibernate.initialize(((Student) user).getGradesMap());
        }
        if (user == null) {
            user = teacherRepository.findByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }

}
