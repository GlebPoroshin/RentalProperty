package com.gleb.rentservice.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRepository<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(T entity) {
        entityManager.persist(entity);
    }

}
