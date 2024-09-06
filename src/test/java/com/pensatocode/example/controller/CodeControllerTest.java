package com.pensatocode.example.controller;

import com.pensatocode.example.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
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
@Import(TestcontainersConfiguration.class)
class RandomCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generateRandomCode_ShouldReturnCode() throws Exception {
        mockMvc.perform(get("/code/generate"))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("\\d{6}")));
    }
}