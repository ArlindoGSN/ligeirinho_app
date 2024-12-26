package com.app.ligeirinho.domain.dtos.company;

import jakarta.validation.constraints.NotBlank;

public record CompanyCreateDto(
    @NotBlank(message = "name is obligatory")
    String name,
    String cnpj,
    @NotBlank(message = "email is obligatory")
    String email,
    @NotBlank(message = "phone is obligatory")
    String phone,
    String address
) {
    
}
