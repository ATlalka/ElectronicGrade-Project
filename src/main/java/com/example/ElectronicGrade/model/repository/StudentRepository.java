package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Address, Long> {
}
