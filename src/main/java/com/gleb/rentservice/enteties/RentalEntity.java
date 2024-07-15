package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
public class RentalEntity extends BaseEntity {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PropertyEntity property;
    private TenantEntity tenant;
    private String cancellationReason; // причина отмены бронирования

    public RentalEntity(LocalDateTime startDate, LocalDateTime endDate, PropertyEntity property, TenantEntity tenant, String cancellationReason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.property = property;
        this.tenant = tenant;
        this.cancellationReason = cancellationReason;
    }

    protected RentalEntity() {}

    @Column(name = "start_date")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    public PropertyEntity getProperty() {
        return property;
    }

    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    public TenantEntity getTenant() {
        return tenant;
    }

    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    @Column(name = "cancellation_reason")
    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
