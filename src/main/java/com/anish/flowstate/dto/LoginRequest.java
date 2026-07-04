package com.anish.flowstate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
