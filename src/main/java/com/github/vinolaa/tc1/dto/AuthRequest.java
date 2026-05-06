package com.github.vinolaa.tc1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @Schema(example = "joaosilva") @NotBlank String login,
    @Schema(example = "Senha@123") @NotBlank String password
) {}