package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "owner")
public class OwnerEntity extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private String contactNumber;
    private Set<PropertyEntity> propertySet;

    public OwnerEntity(String name, String surname, String email, String contactNumber, Set<PropertyEntity> propertySet) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contactNumber = contactNumber;
        this.propertySet = propertySet;
    }

    protected OwnerEntity() {}

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

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "owner")
    public Set<PropertyEntity> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<PropertyEntity> propertySet) {
        this.propertySet = propertySet;
    }
}
