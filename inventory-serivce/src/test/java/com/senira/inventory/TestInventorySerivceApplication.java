package com.senira.inventory;

import org.springframework.boot.SpringApplication;

public class TestInventorySerivceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
