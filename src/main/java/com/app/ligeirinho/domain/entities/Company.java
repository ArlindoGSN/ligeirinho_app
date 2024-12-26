package com.app.ligeirinho.domain.entities;



import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.app.ligeirinho.domain.dtos.company.CompanyCreateDto;
import com.app.ligeirinho.domain.dtos.company.CompanyUpdateDto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;





@Table(name = "companies")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public Company(CompanyCreateDto company) {
        this.name = company.name();
        this.cnpj = company.cnpj();
        this.email = company.email();
        this.phone = company.phone();
        this.address = company.address();
        this.createdAt = LocalDateTime.now();
        this.status = true;
    }

    public Company(UUID id, String name, String cnpj, String email, String address, String phone, LocalDateTime createdAt, LocalDateTime updatedAt, boolean status) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    public Company() {}


    public boolean isActive() {
        return status;
    }

    public void update(CompanyUpdateDto company) {
        if (company.name() != null) {
            this.name = company.name();
        }
        if (company.cnpj() != null) {
            this.cnpj = company.cnpj();
        }
        if (company.email() != null) {
            this.email = company.email();
        }
        if (company.phone() != null) {
            this.phone = company.phone();
        }
        if (company.address() != null) {
            this.address = company.address();
        }
        this.updatedAt = LocalDateTime.now();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
