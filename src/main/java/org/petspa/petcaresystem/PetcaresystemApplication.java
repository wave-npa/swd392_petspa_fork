package org.petspa.petcaresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetcaresystemApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(PetcaresystemApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
