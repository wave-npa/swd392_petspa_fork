package org.petspa.petcaresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PetcaresystemApplication {

	public static void main(String[] args) {
			SpringApplication.run(PetcaresystemApplication.class, args);
	}
}
