package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.enteties.TenantEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends BaseRepository<TenantEntity, Long> {

    TenantEntity findTenantById(Long tenantId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.tenant.id = :tenantId")
    List<ReviewEntity> findReviewsByTenantId(Long tenantId);

    @Modifying
    @Query("UPDATE TenantEntity t SET t.averageRating = :averageRating WHERE t.id = :tenantId")
    int updateTenantAverageRating(Long tenantId, double averageRating);

}
