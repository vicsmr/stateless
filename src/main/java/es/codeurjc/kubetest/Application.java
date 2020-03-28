package es.codeurjc.kubetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		WaitForIt.exec(System.getenv("DATABASE_HOST"),3306);

		SpringApplication.run(Application.class, args);
	}

}
