package com.simso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimsoApplication.class, args);
	}

}
