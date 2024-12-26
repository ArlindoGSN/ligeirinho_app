package com.app.ligeirinho.domain.repositories;

import com.app.ligeirinho.domain.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByIdAndDriverId(int id, int idDriver);
    @Query("""
        SELECT v FROM vehicles v
        JOIN v.driver d
        WHERE d.company.id = :companyId
       """)
    Page<Vehicle> findByCompanyId(@Param("companyId") UUID id, Pageable pageable);

}
