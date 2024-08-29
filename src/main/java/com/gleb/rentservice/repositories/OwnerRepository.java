package com.gleb.rentservice.repositories;


import com.gleb.rentservice.enteties.OwnerEntity;
import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends BaseRepository<OwnerEntity, Long> {

    @Query("SELECT p FROM PropertyEntity p WHERE p.owner.id = :ownerId")
    List<PropertyEntity> findPropertiesByOwnerId(Long ownerId);

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId")
    List<RentalEntity> findRentalsByOwnerId(Long ownerId);
}
