package com.example.rr.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class UserRequestDTO {
    private String email;
    private String password;
    private String role;
}