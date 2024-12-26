package com.app.ligeirinho.services;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.ligeirinho.domain.dtos.company.CompanyCreateDto;
import com.app.ligeirinho.domain.dtos.company.CompanyDetailsDto;
import com.app.ligeirinho.domain.dtos.company.CompanyUpdateDto;
import com.app.ligeirinho.domain.entities.Company;
import com.app.ligeirinho.domain.repositories.CompanyRepository;
import com.app.ligeirinho.services.interfaces.ICompanyServices;

@Service
public class CompanyServices implements ICompanyServices{


    private final CompanyRepository companyRepository;

    
    public CompanyServices(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDetailsDto save(CompanyCreateDto company) {
        if (company.cnpj() != null) {
            if (companyRepository.existsByCnpj(company.cnpj())) {
                throw new IllegalArgumentException("Company with cnpj already exists");
            }
        }
       
        return new CompanyDetailsDto(companyRepository.save(new Company(company)));



    }

    @Override
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
        
    }

    @Override
    public Page<Company> findAll(Pageable page) {
        try {
            return companyRepository.findAll(page);
        } catch (Exception e) {
            throw new IllegalArgumentException("Company not found");
        }
        
    }

    @Override
    public CompanyDetailsDto update(UUID id, CompanyUpdateDto company) {
        try {
            Company companyToUpdate = companyRepository.getReferenceById(id);
            companyToUpdate.update(company);
            return new CompanyDetailsDto(companyRepository.save(companyToUpdate));
        } catch (Exception e) {
            throw new IllegalArgumentException("Company not found");
        }

    }

    @Override
    public boolean existByCnpj(String cnpj) {
        return companyRepository.existsByCnpj(cnpj);
    }

    @Override
    public void delete(UUID id) {
        try {
            Company companyToDelete = companyRepository.getReferenceById(id);
            if (companyToDelete.isActive()) {
                companyToDelete.setStatus(false);
                companyRepository.save(companyToDelete);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Company not found");
        }
        
    }
    
}
