package com.github.vinolaa.tc1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(
    @NotBlank String currentPassword,
    @NotBlank @Size(min = 6) String newPassword,
    @NotBlank @Size(min = 6) String confirmPassword
) {}