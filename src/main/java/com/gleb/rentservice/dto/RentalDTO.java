package com.gleb.rentservice.dto;

import java.time.LocalDateTime;

public class RentalDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long propertyId;
    private Long tenantId;
    private String cancellationReason;

    public RentalDTO(Long id, LocalDateTime startDate, LocalDateTime endDate, Long propertyId, Long tenantId, String cancellationReason) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.cancellationReason = cancellationReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}

