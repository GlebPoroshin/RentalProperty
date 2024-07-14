package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "property")
public class PropertyEntity extends BaseEntity {

    private String address;
    private String description;
    private Double pricePerNight;
    private Set<RentalEntity> rentalSet;
    private Set<ReviewEntity> reviewSet;
    private OwnerEntity owner;

    public PropertyEntity(String address, String description, Double pricePerNight, Set<RentalEntity> rentalSet, Set<ReviewEntity> reviewSet, OwnerEntity owner) {
        this.address = address;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rentalSet = rentalSet;
        this.reviewSet = reviewSet;
        this.owner = owner;
    }

    protected PropertyEntity() {}

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price_per_night")
    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "property")
    public Set<RentalEntity> getRentalSet() {
        return rentalSet;
    }

    public void setRentalSet(Set<RentalEntity> rentalSet) {
        this.rentalSet = rentalSet;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "property")
    public Set<ReviewEntity> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(Set<ReviewEntity> reviewSet) {
        this.reviewSet = reviewSet;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }
}

