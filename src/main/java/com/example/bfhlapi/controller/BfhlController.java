package com.example.bfhlapi.controller;

import com.example.bfhlapi.dto.RequestDto;
import com.example.bfhlapi.dto.ResponseDto;
import com.example.bfhlapi.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    @Autowired
    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> handlePostRequest(@RequestBody RequestDto request) {
        try {
            ResponseDto response = bfhlService.processData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Graceful exception handling
            ResponseDto errorResponse = new ResponseDto();
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}