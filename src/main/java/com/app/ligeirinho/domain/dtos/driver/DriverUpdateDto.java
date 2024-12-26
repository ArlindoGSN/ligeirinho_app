package com.app.ligeirinho.domain.dtos.driver;

import com.app.ligeirinho.domain.entities.Driver;

public record DriverUpdateDto(
    String name,
    String cpf,
    String cnh,
    String email,
    String phone,
    String address
) {


}
