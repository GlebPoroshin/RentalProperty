package com.gleb.rentservice.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
    private Long id;
    private String content;
    private Integer rating;
    private Long propertyId;
    private Long tenantId;
    private Long rentalId;
    private LocalDateTime createdDate;

    public ReviewDTO(Long id, String content, Integer rating, Long propertyId, Long tenantId, Long rentalId, LocalDateTime createdDate) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.rentalId = rentalId;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
