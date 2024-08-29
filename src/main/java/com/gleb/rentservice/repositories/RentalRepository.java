package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.OwnerEntity;
import com.gleb.rentservice.enteties.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends BaseRepository<RentalEntity, Long> {

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate > CURRENT_TIMESTAMP")
    List<RentalEntity> findUpcomingRentalsByOwnerId(Long ownerId);

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.startDate <= CURRENT_TIMESTAMP AND r.endDate >= CURRENT_TIMESTAMP")
    List<RentalEntity> findCurrentRentalsByOwnerId(Long ownerId);

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate > CURRENT_TIMESTAMP")
    List<RentalEntity> findCompletedRentalsByOwnerId(Long ownerId);
}
