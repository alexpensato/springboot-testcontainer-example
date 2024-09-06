package com.pensatocode.example.service;

import com.pensatocode.example.model.SecretKey;
import com.pensatocode.example.repository.SecretKeyRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class SecretKeyService {
    private final SecretKeyRepository repository;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=<>?";
    private static final String ALL_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARS;
    private static final SecureRandom random = new SecureRandom();

    public SecretKeyService(SecretKeyRepository repository) {
        this.repository = repository;
    }

    public Optional<SecretKey> getSecretKeyById(Long id) {
        return repository.findById(id);
    }

    public SecretKey generateAndSaveSecretKey() {
        String code = generateSecretKey();
        SecretKey randomSecretKey = new SecretKey(code);
        return repository.save(randomSecretKey);
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
