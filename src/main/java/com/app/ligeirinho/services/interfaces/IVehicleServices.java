package com.app.ligeirinho.services.interfaces;

import com.app.ligeirinho.domain.dtos.vehicle.VehicleCreateDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleDetailsDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleUpdateDto;
import com.app.ligeirinho.domain.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IVehicleServices {
    public VehicleDetailsDto save(UUID company, int driver, VehicleCreateDto vehicle);
    public Optional<Vehicle> findByIdandDriver(int id, int idDriver);
    public Page<Vehicle> findAllVehicleByCompany(Pageable page, UUID company);
    public VehicleDetailsDto update(int id, int idDriver, VehicleUpdateDto vehicle);
    public void delete(int id, int idDriver);

}
