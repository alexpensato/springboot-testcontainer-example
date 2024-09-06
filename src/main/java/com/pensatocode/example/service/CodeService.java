package com.pensatocode.example.service;

import com.pensatocode.example.model.Code;
import com.pensatocode.example.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodeService {
    private final CodeRepository repository;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=<>?";
    private static final String ALL_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARS;
    private static final SecureRandom random = new SecureRandom();

    public CodeService(CodeRepository repository) {
        this.repository = repository;
    }

    public Code generateAndSaveCode() {
        String code = generateSecretKey();
        Code randomCode = new Code(code);
        return repository.save(randomCode);
    }

    private String generateSecretKey() {
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(ALL_CHARS.length());
            sb.append(ALL_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
