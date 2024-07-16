package com.gleb.rentservice.dto;

import com.gleb.rentservice.enteties.PropertyStatus;

public class PropertyDTO {
    private Long id;
    private String address;
    private String description;
    private Double pricePerNight;
    private PropertyStatus status;
    private Long ownerId;

    public PropertyDTO(Long id, String address, String description, Double pricePerNight, PropertyStatus status, Long ownerId) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
