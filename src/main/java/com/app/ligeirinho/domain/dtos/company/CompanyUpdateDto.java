package com.app.ligeirinho.domain.dtos.company;

public record CompanyUpdateDto(
    String name,
    String cnpj,
    String email,
    String phone,
    String address
) {
    
}
