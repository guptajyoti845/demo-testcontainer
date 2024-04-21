package com.quickdemo.quickdemo.controller;

import com.quickdemo.quickdemo.http.request.StudentRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {

		RestAssured.baseURI = "http://localhost:" + port;
	}

	private static final PostgreSQLContainer<?> postgresTestContainer;

	static {
		postgresTestContainer = new PostgreSQLContainer<>("postgres:15-alpine")
			.withDatabaseName("quickdemo")
			.withUsername("postgres")
			.withReuse(true)
			.withMinimumRunningDuration(Duration.ofSeconds(5))
			.waitingFor(
				Wait.forLogMessage("(.*)database system is ready to accept connections\n", 2));

		postgresTestContainer.start();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {

		registry.add("spring.datasource.url", postgresTestContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresTestContainer::getUsername);
		registry.add("spring.datasource.password", postgresTestContainer::getPassword);
	}

	@Test
	void createStudent() throws Exception {
		StudentRequest studentRequest = new StudentRequest("John", "Doe");

		RestAssured.given()
			.contentType(ContentType.JSON)
			.body(studentRequest)
			.when()
			.post("/api/student")
			.then()
			.statusCode(201);
	}
}
