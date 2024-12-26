package com.app.ligeirinho.domain.dtos.driver;

import com.app.ligeirinho.domain.dtos.company.CompanySumaryDto;
import com.app.ligeirinho.domain.entities.Company;
import com.app.ligeirinho.domain.entities.Driver;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.app.ligeirinho.domain.entities.Driver}
 */
public record DriverDetailsDto(int id,
                               CompanySumaryDto company,
                               String name,
                               String cpf,
                               String cnh,
                               String email,
                               String phone,
                               String address,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt,
                               boolean status) implements Serializable {
  public DriverDetailsDto(Driver driver) {
    this(driver.getId(),
        new CompanySumaryDto(driver.getCompany()),
        driver.getName(),
        driver.getCpf(),
        driver.getCnh(),
        driver.getEmail(),
        driver.getPhone(),
        driver.getAddress(),
        driver.getCreatedAt(),
        driver.getUpdatedAt(),
        driver.isStatus()
    );

  }
}