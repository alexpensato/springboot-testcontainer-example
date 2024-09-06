package com.pensatocode.example.controller;

import com.pensatocode.example.model.Code;
import com.pensatocode.example.service.CodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {
    private final CodeService service;

    public CodeController(CodeService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateCode() {
        Code code = service.generateAndSaveCode();
        return ResponseEntity.ok(code.getValue());
    }
}