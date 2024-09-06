package com.pensatocode.example.controller;

import com.pensatocode.example.TestcontainersConfig;
import com.pensatocode.example.model.SecretKey;
import com.pensatocode.example.repository.SecretKeyRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.matchesPattern;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Import(TestcontainersConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SecretKeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecretKeyRepository repository;

    @AfterAll
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void generateSecretKey_ShouldReturnCode() throws Exception {
        mockMvc.perform(get("/secret-key/generate"))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("^[a-zA-Z0-9!@#$%^&*()_\\-+=<>?]{20}$")));
    }

    @Test
    void getSecretKeyById_ShouldReturnCode_WhenIdExists() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(get("/secret-key/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("ABCdef456789!@#$%^&)"));
    }

    @Test
    void getSecretKeyById_ShouldReturnCode_WhenIdWasJustInserted() throws Exception {
        // Arrange
        SecretKey savedCode = repository.save(new SecretKey("ABCdef456789!@#$%^&)"));
        // Act & Assert
        mockMvc.perform(get("/secret-key/" + savedCode.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("ABCdef456789!@#$%^&)"));
    }

    @Test
    void getSecretKeyById_ShouldReturnNotFound_WhenIdDoesNotExist() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/secret-key/999"))
                .andExpect(status().isNotFound());
    }

}