package com.pensatocode.example.service;

import com.pensatocode.example.model.Code;
import com.pensatocode.example.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodeService {
    private final CodeRepository repository;
    private final SecureRandom random = new SecureRandom();

    public CodeService(CodeRepository repository) {
        this.repository = repository;
    }

    public Code generateAndSaveCode() {
        String code = generateRandomCode();
        Code randomCode = new Code(code);
        return repository.save(randomCode);
    }

    private String generateRandomCode() {
        return String.format("%06d", random.nextInt(1000000));
    }
}
