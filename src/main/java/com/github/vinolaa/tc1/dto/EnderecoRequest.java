package com.github.vinolaa.tc1.dto;

import jakarta.validation.constraints.Size;

public record EnderecoRequest(
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    @Size(max = 2) String uf,
    @Size(max = 8) String cep
) {}