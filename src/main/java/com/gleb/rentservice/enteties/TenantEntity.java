package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tenant")
public class TenantEntity extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private String contactNumber;
    private Set<RentalEntity> rentalSet;
    private Set<ReviewEntity> reviewSet;
    private Double averageRating;

    public TenantEntity(String name, String surname, String email, String contactNumber, Set<RentalEntity> rentalSet, Set<ReviewEntity> reviewSet, Double averageRating) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contactNumber = contactNumber;
        this.rentalSet = rentalSet;
        this.reviewSet = reviewSet;
        this.averageRating = averageRating;
    }

    protected TenantEntity() {}

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "tenant")
    public Set<RentalEntity> getRentalSet() {
        return rentalSet;
    }

    public void setRentalSet(Set<RentalEntity> rentalSet) {
        this.rentalSet = rentalSet;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "tenant")
    public Set<ReviewEntity> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(Set<ReviewEntity> reviewSet) {
        this.reviewSet = reviewSet;
    }

    @Column(name = "average_rating")
    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
