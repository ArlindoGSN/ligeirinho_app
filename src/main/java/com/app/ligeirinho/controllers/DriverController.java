package com.app.ligeirinho.controllers;

import com.app.ligeirinho.domain.dtos.driver.DriverCreateDto;
import com.app.ligeirinho.domain.dtos.driver.DriverDetailsDto;
import com.app.ligeirinho.domain.dtos.driver.DriverSumaryDto;
import com.app.ligeirinho.domain.dtos.driver.DriverUpdateDto;
import com.app.ligeirinho.services.DriverServices;
import com.app.ligeirinho.services.interfaces.IDriverServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/{company}/drivers")
public class DriverController {

    private final IDriverServices driverServices;

    public DriverController(IDriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DriverDetailsDto> createDriver(@RequestBody DriverCreateDto driver, @PathVariable UUID company) {



        var driverDetailsDto = driverServices.save(driver, company);
        String location = "/companies/" + company + "/drivers/" + driverDetailsDto.id();
        return ResponseEntity.created(URI.create(location)).body(driverDetailsDto);
    }
    @GetMapping
    @Transactional
    public ResponseEntity<Page<DriverSumaryDto>> listAllDriver(@PathVariable UUID company, @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<DriverSumaryDto> drivers = driverServices.findAll(pageable, company);
        return ResponseEntity.ok(drivers);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DriverDetailsDto> getDetailsDriver(@PathVariable UUID company, @PathVariable int id) {
        var driver = driverServices.findByIdandCompany(company, id).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        var driverDetailsDto = new DriverDetailsDto(driver);
        return ResponseEntity.ok(driverDetailsDto);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DriverDetailsDto> updateDriver(@PathVariable UUID company, @PathVariable int id, @RequestBody DriverUpdateDto driver) {
        try {
            var driverDetailsDto = driverServices.update(id, driver);
            return ResponseEntity.ok(driverDetailsDto);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID company, @PathVariable int id) {
        driverServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
