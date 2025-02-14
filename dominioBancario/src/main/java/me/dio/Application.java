package me.dio.explorando.um.dominio.bancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(server = {@Server(url = "/", description = "Default Server URL")}) //para contexto do Swagger e corrigir problema de CORS gerados por conflitos https (Railway) e http (OpenAPI Swagger)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(ExplorandoUmDominioBancarioApplication.class, args);
	}

}
