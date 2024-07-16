package com.gleb.rentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class BaseRepository<T, ID> {

    protected JpaRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public List<T> findAll() {
        return repository.findAll();
    }
}
