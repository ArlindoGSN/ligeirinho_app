package com.app.ligeirinho.domain.entities;

import com.app.ligeirinho.domain.dtos.driver.DriverCreateDto;
import com.app.ligeirinho.domain.dtos.driver.DriverUpdateDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Table(name = "drivers")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;
    private String name;
    private String cpf;
    private String cnh;
    private String email;
    private String phone;
    private String address;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public Driver(boolean status, String address, String phone, String email, String cnh, String cpf, String name, Company company, int id) {
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cnh = cnh;
        this.cpf = cpf;
        this.name = name;
        this.company = company;
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }
    public Driver(DriverCreateDto driver) {
        this.name = driver.name();
        this.cpf = driver.cpf();
        this.cnh = driver.cnh();
        this.email = driver.email();
        this.phone = driver.phone();
        this.address = driver.address();
        this.createdAt = LocalDateTime.now();
        this.status = true;
    }
    public void update(DriverUpdateDto driver){
        if (driver.name() != null) {
            this.name = driver.name();
        }
        if (driver.cpf() != null) {
            this.cpf = driver.cpf();
        }
        if (driver.cnh() != null) {
            this.cnh = driver.cnh();
        }
        if (driver.email() != null) {
            this.email = driver.email();
        }
        if (driver.phone() != null) {
            this.phone = driver.phone();
        }
        if (driver.address() != null) {
            this.address = driver.address();
        }
        this.updatedAt = LocalDateTime.now();
    }


    public Driver() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && Objects.equals(company, driver.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", company=" + company +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cnh='" + cnh + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}
