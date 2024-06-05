package com.example.bankservice.controllers.api_impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/s")
    public ResponseEntity<String> xc(){
        return ResponseEntity.ok("HAHA");
    }
}
