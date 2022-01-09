package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.loginData.login = ?1")
    Student findByUsername(String username);

    @Query("select s from Student s where s.studentClass.id = ?1")
    List<Student> findByClassId(Long idClass);
}
