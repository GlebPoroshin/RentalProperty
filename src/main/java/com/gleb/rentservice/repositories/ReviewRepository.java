package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.ReviewEntity;

import java.util.List;

public interface ReviewRepository {
    List<ReviewEntity> findByTenantId(Long tenantId);

    ReviewEntity save(ReviewEntity entity);

    ReviewEntity findById(Long id);

    List<ReviewEntity> findAll();

    List<ReviewEntity> findByPropertyId(Long propertyId);
}
