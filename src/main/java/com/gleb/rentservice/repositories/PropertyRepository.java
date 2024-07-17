package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.ReviewEntity;

import java.util.List;

public interface PropertyRepository {
    List<PropertyEntity> findAllAvailableProperties();

    List<PropertyEntity> findAllByOwnerId(Long ownerId);

    void save(PropertyEntity entity);

    PropertyEntity findById(Long id);

    List<PropertyEntity> findAll();
}
