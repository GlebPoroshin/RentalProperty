package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class ReviewEntity extends BaseEntity {

    private String content;
    private Integer rating;
    private PropertyEntity property;
    private TenantEntity tenant;
    private RentalEntity rental; // аренда, к которой относится отзыв

    public ReviewEntity(String content, Integer rating, PropertyEntity property, TenantEntity tenant, RentalEntity rental) {
        this.content = content;
        this.rating = rating;
        this.property = property;
        this.tenant = tenant;
        this.rental = rental;
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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }
}