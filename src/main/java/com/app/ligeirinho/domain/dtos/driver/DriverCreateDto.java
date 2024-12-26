package com.app.ligeirinho.domain.dtos.driver;

import com.app.ligeirinho.domain.entities.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.app.ligeirinho.domain.entities.Driver}
 */
public record DriverCreateDto(
                              @NotEmpty @NotBlank String name,
                              @NotEmpty @NotBlank String cpf,
                              @NotEmpty @NotBlank String cnh,
                              @NotEmpty @NotBlank String email,
                              @NotEmpty @NotBlank String phone,
                              String address) implements Serializable {

    // Json example
    /*
    {
      "name": "John Doe",
      "cpf": "123456789",
      "cnh": "123456789",
      "email": "c7sZb@example.com",
      "phone": "123456789",
      "address": "123 Main St"
    }

     */
}