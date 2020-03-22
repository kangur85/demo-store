package eu.kaszkowiak.demostore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class DemoStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStoreApplication.class, args);
	}

}
