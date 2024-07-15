package org.petspa.petcaresystem;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.role.model.Role;
import org.petspa.petcaresystem.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = {"org.petspa.petcaresystem", "org.springframework.security.crypto.password"})
@PropertySource("classpath:application.properties")
public class PetcaresystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PetcaresystemApplication.class, args);
	}
}