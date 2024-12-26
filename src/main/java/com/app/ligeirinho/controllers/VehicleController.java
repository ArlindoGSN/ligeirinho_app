package com.app.ligeirinho.controllers;

import com.app.ligeirinho.domain.dtos.vehicle.VehicleCreateDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleDetailsDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleUpdateDto;
import com.app.ligeirinho.services.interfaces.IVehicleServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/company/{companyId}")
public class VehicleController {

    private final IVehicleServices vehicleServices;

    public VehicleController(IVehicleServices vehicleServices) {
        this.vehicleServices = vehicleServices;
    }



    @PostMapping("/driver/{driverID}")
    @Transactional
    public ResponseEntity<VehicleDetailsDto> createVehicle(@PathVariable UUID companyId, @PathVariable int driverID, @RequestBody VehicleCreateDto vehicle) {
        var vehicleDetailsDto = vehicleServices.save(companyId, driverID, vehicle);
        String location = "/companies/" + companyId + "/drivers/" + driverID + "/vehicles/" + vehicleDetailsDto.id();
        return ResponseEntity.created(URI.create(location)).body(vehicleDetailsDto);
    }
    @GetMapping
    @Transactional
    public ResponseEntity<Page<VehicleDetailsDto>> listAllVehicleByCompany(@PathVariable UUID companyId, @PageableDefault(size = 10) Pageable pageable) {
        var vehicles = vehicleServices.findAllVehicleByCompany(pageable, companyId).map(VehicleDetailsDto::new);
        return ResponseEntity.ok(vehicles);
    }
    @GetMapping("/driver/{driverID}/{id}")
    @Transactional
    public ResponseEntity<VehicleDetailsDto> getDetailsVehicle(@PathVariable UUID companyId, @PathVariable int driverID, @PathVariable int id) {
        var vehicle = vehicleServices.findByIdandDriver(id, driverID).orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
        var vehicleDetailsDto = new VehicleDetailsDto(vehicle);
        return ResponseEntity.ok(vehicleDetailsDto);
    }
    @PutMapping("/driver/{driverID}/{id}")
    @Transactional
    public ResponseEntity<VehicleDetailsDto> updateVehicle(@PathVariable UUID companyId, @PathVariable int driverID, @PathVariable int id, @RequestBody VehicleUpdateDto vehicle) {
        var vehicleDetailsDto = vehicleServices.update(id, driverID, vehicle);
        return ResponseEntity.ok(vehicleDetailsDto);
    }
    @DeleteMapping("/driver/{driverID}/{id}")
    @Transactional
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID companyId, @PathVariable int driverID, @PathVariable int id) {
        vehicleServices.delete(id, driverID);
        return ResponseEntity.noContent().build();
    }

}
