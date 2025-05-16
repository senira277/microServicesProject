package com.senira.order_service;

import com.senira.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)


class OrderServiceApplicationTests {
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}
	@Test
	void shouldSubmitOrder() {
		String orderRequest = """
				{
					"skuCode": "iphone_13",
					"quantity": 1,
					"price": 1000.00
				}
				""";
		InventoryClientStub.stubInventoryCall("iphone_13", 1);
		var response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.body(orderRequest)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				.extract()
				.asString();

		assertThat(response, Matchers.is("Order placed successfully"));
	}

}