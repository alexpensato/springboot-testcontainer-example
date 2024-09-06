package com.pensatocode.example.controller;

import com.pensatocode.example.model.SecretKey;
import com.pensatocode.example.service.SecretKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret-key")
public class SecretKeyController {
    private final SecretKeyService service;

    public SecretKeyController(SecretKeyService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateCode() {
        SecretKey secretKey = service.generateAndSaveSecretKey();
        return ResponseEntity.ok(secretKey.getValue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSecretKeyById(@PathVariable Long id) {
        return service.getSecretKeyById(id)
                .map(secretKey -> ResponseEntity.ok(secretKey.getValue()))
                .orElse(ResponseEntity.notFound().build());
    }
}