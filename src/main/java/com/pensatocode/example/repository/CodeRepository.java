package com.pensatocode.example.repository;

import com.pensatocode.example.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
}