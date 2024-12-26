package com.app.ligeirinho.domain.entities;

import com.app.ligeirinho.domain.dtos.vehicle.VehicleCreateDto;
import com.app.ligeirinho.domain.dtos.vehicle.VehicleUpdateDto;
import jakarta.persistence.*;

import java.util.Objects;

@Table
@Entity(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    Driver driver;
    String plate;
    String model;
    String color;
    int capacity;
    boolean status;


    public Vehicle(VehicleCreateDto vehicle) {
        this.plate = vehicle.plate();
        this.model = vehicle.model();
        this.color = vehicle.color();
        this.capacity = vehicle.capacity();
        this.status = true;
    }
    public void update(VehicleUpdateDto vehicle) {
        this.plate = vehicle.plate();
        this.model = vehicle.model();
        this.capacity = vehicle.capacity();
        this.color = vehicle.color();
    }

    public Vehicle(int id, Driver driver, String plate, String model, String color, int capacity,boolean status) {
        this.id = id;
        this.driver = driver;
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.capacity = capacity;
        this.status = status;
    }
    public Vehicle() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
