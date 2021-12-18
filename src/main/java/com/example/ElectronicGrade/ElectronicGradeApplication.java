package com.example.ElectronicGrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class ElectronicGradeApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ElectronicGradeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		String sql = "INSERT INTO adresy (Ulica, NumerDomu, Miejscowosc, KodPocztowy) VALUES (?, ?, ?, ?)";

		int result = jdbcTemplate.update(sql, "Magellana", 73, "Wrocław", "30-900");

		if (result > 0) {
			System.out.println("A new row has been insterted");
		}
	}
}
