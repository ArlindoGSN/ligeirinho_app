package com.app.ligeirinho.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ligeirinho.domain.dtos.company.CompanyCreateDto;
import com.app.ligeirinho.domain.dtos.company.CompanyDetailsDto;
import com.app.ligeirinho.domain.dtos.company.CompanySumaryDto;
import com.app.ligeirinho.domain.dtos.company.CompanyUpdateDto;
import com.app.ligeirinho.services.CompanyServices;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final CompanyServices companyServices;


    public CompanyController(CompanyServices companyServices) {
        this.companyServices = companyServices;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompanyDetailsDto> createCompany(@RequestBody CompanyCreateDto company) {


        try {
            var companyDetailsDto = companyServices.save(company);
            String location = "/companies/" + companyDetailsDto.id();
            return ResponseEntity.created(URI.create(location)).body(companyDetailsDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<CompanySumaryDto>> listAllCompany(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
        var companies = companyServices.findAll(pageable).map(CompanySumaryDto::new);
        return ResponseEntity.ok(companies);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<CompanyDetailsDto> getDetailsCompany(@PathVariable UUID id) {
        var company = companyServices.findById(id).map(CompanyDetailsDto::new);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CompanyDetailsDto> updateCompany(@PathVariable UUID id, @RequestBody CompanyUpdateDto entity) {
        try{
            var company = companyServices.update(id, entity);
            return ResponseEntity.ok(company);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        companyServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    
}
