package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class ReviewEntity extends BaseEntity {
    private String content;
    private Integer rating;
    private PropertyEntity property;
    private TenantEntity tenant;
    private RentalEntity rental;
    private LocalDateTime createdDate;

    public ReviewEntity(String content, Integer rating, PropertyEntity property, TenantEntity tenant, RentalEntity rental) {
        this.content = content;
        this.rating = rating;
        this.property = property;
        this.tenant = tenant;
        this.rental = rental;
        this.createdDate = LocalDateTime.now();
    }

    protected ReviewEntity() {}

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    public PropertyEntity getProperty() {
        return property;
    }

    public void setProperty(PropertyEntity property) {
        this.property = property;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    public TenantEntity getTenant() {
        return tenant;
    }

    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }

    @Column(name = "created_date")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
