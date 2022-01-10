package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
