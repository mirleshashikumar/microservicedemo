package com.example.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan("com.example.department")
@EnableJpaRepositories(basePackages = "com.example.department.repository")
@EntityScan("com.example.department.model")
@EnableEurekaClient
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}
}
