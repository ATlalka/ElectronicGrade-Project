package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.loginData.login = ?1")
    Student findByUsername(String username);
}
