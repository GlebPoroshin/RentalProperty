package com.gleb.rentservice.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseMethods <T, ID> {
    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();
}
