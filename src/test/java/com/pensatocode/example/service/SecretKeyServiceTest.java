package com.pensatocode.example.service;

import com.pensatocode.example.model.SecretKey;
import com.pensatocode.example.repository.SecretKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SecretKeyServiceTest {

    @Mock
    private SecretKeyRepository repository;

    private SecretKeyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SecretKeyService(repository);
    }

    @Test
    void generateAndSaveCode_ShouldReturnRandomSecretKeyWithCorrectFormat() {
        // Arrange
        when(repository.save(any(SecretKey.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        SecretKey result = service.generateAndSaveSecretKey();

        // Assert
        assertNotNull(result);
        assertNotNull(result.getValue());
        assertEquals(20, result.getValue().length());
        assertTrue(result.getValue().matches("^[a-zA-Z0-9!@#$%^&*()_\\-+=<>?]{20}$"));
    }
}

