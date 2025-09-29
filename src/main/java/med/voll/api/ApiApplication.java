package med.voll.api;

import org.springframework.boot.SpringApplication; // Classe que fornece uma maneira de iniciar uma aplicação Spring a partir do método main.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Anotação que habilita a autoconfiguração do Spring Boot e a varredura de componentes.

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
