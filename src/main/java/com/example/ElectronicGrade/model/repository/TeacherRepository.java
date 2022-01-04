package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.users.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("select t from Teacher t where t.loginData.login = ?1")
    Teacher findByUsername(String username);
}
