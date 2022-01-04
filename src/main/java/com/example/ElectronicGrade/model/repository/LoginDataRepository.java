package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData, Long> {
}
