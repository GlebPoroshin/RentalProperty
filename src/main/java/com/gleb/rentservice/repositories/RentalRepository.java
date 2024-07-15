package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate > CURRENT_TIMESTAMP")
    List<RentalEntity> findUpcomingRentalsByOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.startDate <= CURRENT_TIMESTAMP AND r.endDate >= CURRENT_TIMESTAMP")
    List<RentalEntity> findCurrentRentalsByOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate <= CURRENT_TIMESTAMP")
    List<RentalEntity> findCompletedRentalsByOwnerId(@Param("ownerId") Long ownerId);
}
