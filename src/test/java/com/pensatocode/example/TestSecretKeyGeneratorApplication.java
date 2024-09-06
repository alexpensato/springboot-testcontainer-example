package com.pensatocode.example;

import org.springframework.boot.SpringApplication;

public class TestSecretKeyGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.from(SecretKeyGeneratorApplication::main).with(TestcontainersConfig.class).run(args);
	}

}
