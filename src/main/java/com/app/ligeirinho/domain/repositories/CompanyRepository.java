package com.app.ligeirinho.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ligeirinho.domain.entities.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    boolean existsByCnpj(String cnpj);
    
}
