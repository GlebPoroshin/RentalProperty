package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.enteties.TenantEntity;

import java.util.List;

public interface TenantRepository {
    TenantEntity findTenantById(Long tenantId);

    List<ReviewEntity> findReviewsByTenantId(Long tenantId);

    void updateTenantAverageRating(Long tenantId, double averageRating);

    TenantEntity save(TenantEntity entity);

    TenantEntity findById(Long id);

    List<TenantEntity> findAll();
}
