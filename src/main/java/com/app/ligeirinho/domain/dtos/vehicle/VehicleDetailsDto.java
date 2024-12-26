package com.app.ligeirinho.domain.dtos.vehicle;

import com.app.ligeirinho.domain.dtos.driver.DriverSumaryDto;
import com.app.ligeirinho.domain.entities.Vehicle;

public record VehicleDetailsDto(
        int id,
        DriverSumaryDto driver,
        String plate,
        String color,
        String model,
        int capacity

) {
    public VehicleDetailsDto(Vehicle vehicle) {
        this(vehicle.getId(), new DriverSumaryDto(vehicle.getDriver()), vehicle.getPlate(), vehicle.getColor(), vehicle.getModel(), vehicle.getCapacity());
    }
}
