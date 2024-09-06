package com.pensatocode.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfig.class)
@SpringBootTest
class SecretKeyGeneratorApplicationTests {

	@Test
	void contextLoads() {
	}

}
