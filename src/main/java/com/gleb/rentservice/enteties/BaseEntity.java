package com.gleb.rentservice.enteties;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
