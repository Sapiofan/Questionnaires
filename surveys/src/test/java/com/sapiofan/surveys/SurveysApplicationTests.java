package com.sapiofan.surveys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"h2db", "debug"})
class SurveysApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate rest;

	@Test
	void contextLoads() {
		assertNotNull(rest);
		assertNotEquals(0, port);
	}



}
