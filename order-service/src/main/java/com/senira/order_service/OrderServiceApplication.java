package com.senira.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication


public class OrderServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);

		log.info("Order Service Application Started");
	}

}
