package com.vb.autohubapi;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class AutohubApiApplication {

	public static void main(String[] args) {
		log.info("Starting AutohubApiApplication");

		SpringApplication.run(AutohubApiApplication.class, args);
	}

}
