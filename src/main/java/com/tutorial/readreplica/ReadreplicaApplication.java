package com.tutorial.readreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.tutorial.readreplica.model"})
@EnableJpaRepositories(basePackages = {"com.tutorial.readreplica.repository"})
@EnableAutoConfiguration
@SpringBootApplication
public class ReadreplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadreplicaApplication.class, args);
	}

}
