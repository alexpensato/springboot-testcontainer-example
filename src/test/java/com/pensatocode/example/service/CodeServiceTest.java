package com.pensatocode.example.service;

import com.pensatocode.example.model.Code;
import com.pensatocode.example.repository.CodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CodeServiceTest {

    @Mock
    private CodeRepository repository;

    private CodeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CodeService(repository);
    }

    @Test
    void generateAndSaveCode_ShouldReturnRandomCode() {
        // Arrange
        Code expectedCode = new Code("123456");
        when(repository.save(any(Code.class))).thenReturn(expectedCode);

        // Act
        Code result = service.generateAndSaveCode();

        // Assert
        assertNotNull(result);
        assertEquals(expectedCode.getValue(), result.getValue());
    }
}

