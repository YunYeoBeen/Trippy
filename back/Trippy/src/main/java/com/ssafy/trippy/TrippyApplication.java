package com.ssafy.trippy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TrippyApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:/application.yml,"
			+ "classpath:/application-aws.yml,"
			+ "classpath:/application-jwt.yml,"
			+ "classpath:/email.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(TrippyApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
