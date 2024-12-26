package com.app.ligeirinho.domain.dtos.company;

import java.util.UUID;

import com.app.ligeirinho.domain.entities.Company;

public record CompanySumaryDto(
    UUID id,
    String name
) {

    public CompanySumaryDto(Company company) {
        this(company.getId(), company.getName());
    }
    
}
