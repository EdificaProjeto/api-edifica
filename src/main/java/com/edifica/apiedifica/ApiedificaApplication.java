package com.edifica.apiedifica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Edifica", version = "1.0", description = "API do projeto Edifica") )
public class ApiedificaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiedificaApplication.class, args);
	}

}
