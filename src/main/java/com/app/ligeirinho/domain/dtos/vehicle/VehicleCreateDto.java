package com.app.ligeirinho.domain.dtos.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VehicleCreateDto(
        @NotBlank(message = "plate is obligatory") String plate,
        @NotBlank(message = "model is obligatory") String model,
        @NotBlank(message = "color is obligatory") String color,
        @NotNull(message = "capacity is obligatory") @Positive int capacity
) {

    // Json Examples

    /*
    {
        "plate": "ABC-1234",
        "model": "Sedan",
        "color": "Red",
        "capacity": 4
    }
    */

    /*
    {
        "plate": "ABC-1234",
        "model": "Sedan",
        "color": "Red",
        "capacity": 4
    }
    */

}
