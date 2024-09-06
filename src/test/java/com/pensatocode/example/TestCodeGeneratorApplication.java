package com.pensatocode.example;

import org.springframework.boot.SpringApplication;

public class TestCodeGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.from(CodeGeneratorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
