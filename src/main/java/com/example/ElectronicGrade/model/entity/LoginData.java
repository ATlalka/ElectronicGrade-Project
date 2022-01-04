package com.example.ElectronicGrade.model.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "DaneLogowania")
public class LoginData {

    @Id
    @GeneratedValue
    @Column(name = "idDaneLogowania")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "haslo", nullable = false, length = 64)
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEncryptedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

}
