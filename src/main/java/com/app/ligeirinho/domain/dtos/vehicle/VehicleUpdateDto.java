package com.app.ligeirinho.domain.dtos.vehicle;

public record VehicleUpdateDto(
        String plate,
        String model,
        String color,
        int capacity
) {
}
