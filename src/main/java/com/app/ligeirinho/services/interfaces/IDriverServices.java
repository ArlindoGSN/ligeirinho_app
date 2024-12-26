package com.app.ligeirinho.services.interfaces;

import com.app.ligeirinho.domain.dtos.driver.DriverCreateDto;
import com.app.ligeirinho.domain.dtos.driver.DriverDetailsDto;
import com.app.ligeirinho.domain.dtos.driver.DriverSumaryDto;
import com.app.ligeirinho.domain.dtos.driver.DriverUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IDriverServices {

    public DriverDetailsDto save(DriverCreateDto driver, UUID company);
    public Optional<DriverDetailsDto> findByIdandCompany(UUID company, int id);
    public Page<DriverSumaryDto> findAll(Pageable page, UUID company);
    public DriverDetailsDto update(int id, DriverUpdateDto driver);
    public void delete(int id);
}
