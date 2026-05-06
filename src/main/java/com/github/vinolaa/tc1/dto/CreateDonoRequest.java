package com.github.vinolaa.tc1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDonoRequest(
    @NotBlank String name,
    @Email @NotBlank String email,
    @NotBlank String login,
    @NotBlank @Size(min = 6) String password,
    @NotBlank @Size(min = 14, max = 14) String cnpj,
    EnderecoRequest endereco
) {}