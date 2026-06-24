package com.example.bfhlapi.controller; // Note: change 'bfhlapi' to 'demo' if you kept that naming!

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*") // Allows your frontend to call this without CORS errors
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> checkHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "active");
        response.put("message", "The API is running smoothly!");

        return ResponseEntity.ok(response);
    }
}