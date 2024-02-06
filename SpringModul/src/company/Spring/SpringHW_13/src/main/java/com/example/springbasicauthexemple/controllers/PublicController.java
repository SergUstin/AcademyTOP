package com.example.springbasicauthexemple.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Осуществлен вызов публичного метода!");
    }
}
