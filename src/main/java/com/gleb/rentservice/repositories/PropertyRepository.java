package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.PropertyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends BaseRepository<PropertyEntity, Long> {

    @Query("SELECT p FROM PropertyEntity p WHERE p.status = 'AVAILABLE'")
    List<PropertyEntity> findAllAvailableProperties();

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate <= CURRENT_TIMESTAMP")
    List<PropertyEntity> findAllByOwnerId(Long ownerId);
}
