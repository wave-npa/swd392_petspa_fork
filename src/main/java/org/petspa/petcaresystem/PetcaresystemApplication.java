package org.petspa.petcaresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@PropertySource("classpath:application.properties")
public class PetcaresystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {

		SpringApplication.run(PetcaresystemApplication.class, args);
	}
}
