package com.example.ElectronicGrade.model.entity.users;

import com.example.ElectronicGrade.model.entity.LoginData;
import com.example.ElectronicGrade.model.entity.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "uzytkownicy")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

    private static final String ROLE = "STUDENT";

    @Id
    @GeneratedValue
    @Column(name = "idUzytkownik")
    private Long id;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DaneLogowaniaidDaneLogowania", referencedColumnName = "idDaneLogowania")
    private LoginData loginData;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OsobyidOsoba", referencedColumnName = "idOsoba")
    private Person person;

    public abstract Collection<Role> getRoles();

    public LoginData getLoginData() {
        return loginData;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return person.getName() + " " + person.getSurname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return loginData.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return loginData.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
