package com.example.SpringJPA.JPA;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;  // 1. Add this import

@SpringBootApplication
@Log
public class JpaApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplication.run(JpaApplication.class, args);
	}

}