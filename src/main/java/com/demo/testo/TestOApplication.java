package com.demo.testo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
@EnableJpaRepositories("com.demo.testo.*")
@ComponentScan(basePackages = { "com.demo.testo.*" })
@EntityScan("com.demo.testo.*")
public class TestOApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestOApplication.class, args);
	}

}
