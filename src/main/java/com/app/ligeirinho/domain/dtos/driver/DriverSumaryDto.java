package com.app.ligeirinho.domain.dtos.driver;

import com.app.ligeirinho.domain.dtos.company.CompanySumaryDto;
import com.app.ligeirinho.domain.entities.Driver;

import java.io.Serializable;

/**
 * DTO for {@link com.app.ligeirinho.domain.entities.Driver}
 */
public record DriverSumaryDto(int id, CompanySumaryDto company, String name) implements Serializable {
    public DriverSumaryDto(Driver driver) {
        this(driver.getId(), new CompanySumaryDto(driver.getCompany()), driver.getName());
    }


}