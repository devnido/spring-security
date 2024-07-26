package com.example.security.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(
    @NotBlank String username,
    @NotBlank String password,
    @Valid AuthRegisterRoleRequest roleRequest) {
}
