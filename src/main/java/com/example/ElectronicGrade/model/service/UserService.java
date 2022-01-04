package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.users.User;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import com.example.ElectronicGrade.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRoles().stream().map(Enum::name).collect(Collectors.joining()));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

}
