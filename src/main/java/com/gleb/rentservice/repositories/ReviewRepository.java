package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends BaseRepository<ReviewEntity, Long> {

    @Query("SELECT r FROM ReviewEntity r WHERE r.tenant.id = :tenantId")
    List<ReviewEntity> findByTenantId(Long tenantId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.property.id = :propertyId")
    List<ReviewEntity> findByPropertyId(Long propertyId);
}
