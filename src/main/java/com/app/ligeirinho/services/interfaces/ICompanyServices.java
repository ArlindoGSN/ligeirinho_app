package com.app.ligeirinho.services.interfaces;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.ligeirinho.domain.dtos.company.CompanyCreateDto;
import com.app.ligeirinho.domain.dtos.company.CompanyDetailsDto;
import com.app.ligeirinho.domain.dtos.company.CompanyUpdateDto;
import com.app.ligeirinho.domain.entities.Company;



public interface ICompanyServices {
    public CompanyDetailsDto save(CompanyCreateDto company);
    public Optional<Company> findById(UUID id);
    public Page<Company> findAll(Pageable page);
    public CompanyDetailsDto update(UUID id, CompanyUpdateDto company);
    public boolean existByCnpj(String cnpj); 
    public void delete(UUID id);
}
