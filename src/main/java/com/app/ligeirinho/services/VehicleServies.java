package com.app.ligeirinho.services;

import com.app.ligeirinho.domain.dtos.vehicle.VehicleCreateDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleDetailsDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleUpdateDto;
import com.app.ligeirinho.domain.entities.Vehicle;
import com.app.ligeirinho.domain.repositories.VehicleRepository;
import com.app.ligeirinho.services.interfaces.IVehicleServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class VehicleServies implements IVehicleServices {
    private final VehicleRepository vehicleRepository;
    private final DriverServices driverServices;
    private final CompanyServices companyServices;

    public VehicleServies(VehicleRepository vehicleRepository, DriverServices driverServices, CompanyServices companyServices) {
        this.vehicleRepository = vehicleRepository;
        this.driverServices = driverServices;
        this.companyServices = companyServices;
    }

    @Override
    public VehicleDetailsDto save(UUID company, int driver, VehicleCreateDto vehicle) {
        try {
            var companyFound = companyServices.findById(company).orElseThrow(
                    () -> new IllegalArgumentException("Company not found"));
            var driverFound = driverServices.findByIdandCompany(company, driver).orElseThrow(
                    () -> new IllegalArgumentException("Driver not found"));
            var vehicleToSave = new Vehicle(vehicle);
            vehicleToSave.setDriver(driverFound);
            vehicleRepository.save(vehicleToSave);
            return new VehicleDetailsDto(vehicleToSave);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    @Override
    public Optional<Vehicle> findByIdandDriver(int id, int idDriver) {
        try {
            return vehicleRepository.findByIdAndDriverId(id, idDriver);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Page<Vehicle> findAllVehicleByCompany(Pageable page, UUID company) {
        try {
            return vehicleRepository.findByCompanyId(company, page);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public VehicleDetailsDto update(int id, int idDriver, VehicleUpdateDto vehicle) {
        try {
            var vehicleFound = findByIdandDriver(id, idDriver).orElseThrow(
                    () -> new IllegalArgumentException("Vehicle not found"));
            vehicleFound.update(vehicle);
            vehicleRepository.save(vehicleFound);
            return new VehicleDetailsDto(vehicleFound);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void delete(int id, int idDriver) {
        try {
            var vehicleFound = findByIdandDriver(id, idDriver).orElseThrow(
                    () -> new IllegalArgumentException("Vehicle not found"));
            vehicleFound.setStatus(false);
            vehicleRepository.save(vehicleFound);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
