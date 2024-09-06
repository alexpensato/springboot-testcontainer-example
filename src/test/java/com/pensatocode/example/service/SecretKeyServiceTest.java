package com.pensatocode.example.service;

import com.pensatocode.example.model.SecretKey;
import com.pensatocode.example.repository.SecretKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    void getSecretKeyById_ShouldReturnSecretKey_WhenIdExists() {
        // Arrange
        Long id = 1L;
        SecretKey expectedSecretKey = new SecretKey("ABCDEFGHIJ1234567890!@#$%^&*()abcde");
        when(repository.findById(id)).thenReturn(Optional.of(expectedSecretKey));
        // Act
        Optional<SecretKey> result = service.getSecretKeyById(id);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedSecretKey.getValue(), result.get().getValue());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getSecretKeyById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        // Arrange
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        // Act
        Optional<SecretKey> result = service.getSecretKeyById(id);
        // Assert
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findById(id);
    }
}

