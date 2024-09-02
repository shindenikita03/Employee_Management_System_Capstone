package com.capstone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "Department_Capstone", version = "1.0", 
description = "Documentation API Gateway v1.0",
contact = @Contact(name ="Capstone",email="capstone@gmail.com" ),
license = @License(name="Apache2.0")))
@SpringBootApplication
public class DepartmentCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentCapstoneApplication.class, args);
	}
	
	@Bean
	public ModelMapper getMmodelMapper() {
		return new ModelMapper();
	}
	

}
