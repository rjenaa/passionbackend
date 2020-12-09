package com.batch.businessbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusinessbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessbatchApplication.class, args);
	}

}
