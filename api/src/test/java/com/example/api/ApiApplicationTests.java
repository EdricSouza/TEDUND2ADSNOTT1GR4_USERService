package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"app.auth.jwt-secret=chave-apenas-para-testes-com-32-bytes",
		"spring.datasource.url=jdbc:h2:mem:context_test;DB_CLOSE_DELAY=-1"
})
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
