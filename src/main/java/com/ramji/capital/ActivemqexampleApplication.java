package com.ramji.capital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActivemqexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqexampleApplication.class, args);
	}

}

