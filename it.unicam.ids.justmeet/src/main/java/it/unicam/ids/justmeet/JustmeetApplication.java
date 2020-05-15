package it.unicam.ids.justmeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JustmeetApplication {

	public static void main(String[] args) {

		SpringApplication.run(JustmeetApplication.class, args);
	}

}
