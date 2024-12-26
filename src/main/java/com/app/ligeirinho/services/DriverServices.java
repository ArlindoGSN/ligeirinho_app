package com.app.ligeirinho.services;

import com.app.ligeirinho.domain.dtos.driver.DriverCreateDto;
import com.app.ligeirinho.domain.dtos.driver.DriverDetailsDto;
import com.app.ligeirinho.domain.dtos.driver.DriverSumaryDto;
import com.app.ligeirinho.domain.dtos.driver.DriverUpdateDto;
import com.app.ligeirinho.domain.entities.Driver;
import com.app.ligeirinho.domain.repositories.DriverRepository;
import com.app.ligeirinho.services.interfaces.IDriverServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class DriverServices implements IDriverServices {
    private final DriverRepository repository;
    private final CompanyServices companyServices;

    public DriverServices(DriverRepository repository, CompanyServices companyServices) {
        this.repository = repository;
        this.companyServices = companyServices;
    }


    @Override
    public DriverDetailsDto save(DriverCreateDto driver, UUID company) {

        var companyFound = companyServices.findById(company).orElseThrow(() -> new IllegalArgumentException("Company not found"));

        if (!companyFound.isActive()) {
            throw new IllegalArgumentException("Company not active");
        }
        var driverToSave = new Driver(driver);
        driverToSave.setCompany(companyFound);

        return new DriverDetailsDto(repository.save(driverToSave));

    }

    @Override
    public Optional<DriverDetailsDto> findByIdandCompany(UUID company, int id) {
        return repository.findByIdAndCompanyId(id, company).map(DriverDetailsDto::new);
    }

    @Override
    public Page<DriverSumaryDto> findAll(Pageable page, UUID company) {
        return repository.findAllByCompany_Id(company, page).map(DriverSumaryDto::new);
    }

    @Override
    public DriverDetailsDto update(int id, DriverUpdateDto driver) {
        var driverFound = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        driverFound.update(driver);
        return new DriverDetailsDto(repository.save(driverFound));

    }

    @Override
    public void delete(int id) {
        var driverFound = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        driverFound.setStatus(false);
        repository.save(driverFound);
    }
}
