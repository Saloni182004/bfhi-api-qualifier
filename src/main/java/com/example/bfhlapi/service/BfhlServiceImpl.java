package com.example.bfhlapi.service;

import com.example.bfhlapi.dto.RequestDto;
import com.example.bfhlapi.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto processData(RequestDto request) {
        List<String> data = request.getData();
        if (data == null) data = new ArrayList<>();

        List<String> evens = new ArrayList<>();
        List<String> odds = new ArrayList<>();
        List<String> alphas = new ArrayList<>();
        List<String> specials = new ArrayList<>();
        long sum = 0;
        StringBuilder alphaChars = new StringBuilder();

        for (String item : data) {
            if (item == null || item.isEmpty()) continue;

            // Check if it's a number (including negative numbers)
            if (item.matches("^-?\\d+$")) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evens.add(item);
                } else {
                    odds.add(item);
                }
            }
            // Check if it's strictly alphabetical
            else if (item.matches("^[a-zA-Z]+$")) {
                alphas.add(item.toUpperCase());
                alphaChars.append(item);
            }
            // Everything else goes to special characters
            else {
                specials.add(item);
            }
        }

        // Logic for concat_string: Reverse the collected alphabetic characters and apply alternating caps
        String reversed = alphaChars.reverse().toString();
        StringBuilder concatResult = new StringBuilder();
        boolean upper = true;

        for (char c : reversed.toCharArray()) {
            if (upper) {
                concatResult.append(Character.toUpperCase(c));
            } else {
                concatResult.append(Character.toLowerCase(c));
            }
            upper = !upper; // Toggle the flag for alternating casing
        }

        ResponseDto response = new ResponseDto();
        response.setSuccess(true);

        // IMPORTANT: Change these details to your actual credentials!
        response.setUserId("john_doe_17091999");
        response.setEmail("john@xyz.com");
        response.setRollNumber("ABCD123");

        response.setEvenNumbers(evens);
        response.setOddNumbers(odds);
        response.setAlphabets(alphas);
        response.setSpecialCharacters(specials);
        response.setSum(String.valueOf(sum));
        response.setConcatString(concatResult.toString());

        return response;
    }
}