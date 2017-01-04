package com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value="com.desafio.security")
public class DesafioApiResTfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApiResTfulApplication.class, args);
	}
}
