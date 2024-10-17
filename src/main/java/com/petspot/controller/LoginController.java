package com.petspot.controller;

import com.petspot.dto.login.LoginDTO;
import com.petspot.model.Login;
import com.petspot.repository.LoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginRepository repository;

    @PostMapping
    public ResponseEntity<Map<String, String>> signIn(@RequestBody @Valid LoginDTO loginDTO) {
        Login auth = repository.findByEmailAndPassword(loginDTO.email(), loginDTO.password());

        if (auth != null) {
            // Retorna o ID do tutor no corpo da resposta
            Map<String, String> response = new HashMap<>();
            response.put("ownerId", auth.getOwnerId());
            return ResponseEntity.ok(response);
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid credentials, please check your details and try again.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}