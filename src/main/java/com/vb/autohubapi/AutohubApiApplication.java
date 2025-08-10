package com.vb.autohubapi;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class AutohubApiApplication {

	@Autowired
    private Environment env;

	public static void main(String[] args) {
		log.info("Starting AutohubApiApplication");

		SpringApplication.run(AutohubApiApplication.class, args);
	}

	@PostConstruct
    public void printActiveProfiles() {
        System.out.println("Active profiles: " + String.join(", ", env.getActiveProfiles()));
    }

}
