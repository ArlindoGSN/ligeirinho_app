package com.app.ligeirinho.domain.repositories;

import com.app.ligeirinho.domain.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Page<Driver> findAllByCompany_Id(UUID companyId, Pageable pageable);
    Optional<Driver> findByIdAndCompanyId(int id, UUID companyId);


}
