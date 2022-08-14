package com.example.amazon.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ApiResponse {

    private String message;
    private int status;
}