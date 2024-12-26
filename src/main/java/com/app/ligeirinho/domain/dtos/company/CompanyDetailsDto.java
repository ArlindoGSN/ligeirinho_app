package com.app.ligeirinho.domain.dtos.company;


import java.time.LocalDateTime;
import java.util.UUID;

import com.app.ligeirinho.domain.entities.Company;




public record CompanyDetailsDto(
    UUID id,
    String name,
    String cnpj,
    String email,
    String phone,
    String address,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    boolean status
) {
    public CompanyDetailsDto(Company company) {
        this(company.getId(),
         company.getName(),
          company.getCnpj(),
           company.getEmail(),
            company.getPhone(),
             company.getAddress(),
              company.getCreatedAt(),
               company.getUpdatedAt(),
                company.isActive());
    }

}
