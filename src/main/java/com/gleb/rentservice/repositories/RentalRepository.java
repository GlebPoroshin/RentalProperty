package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.RentalEntity;

import java.util.List;

public interface RentalRepository {
    List<RentalEntity> findUpcomingRentalsByOwnerId(Long ownerId);

    List<RentalEntity> findCurrentRentalsByOwnerId(Long ownerId);

    List<RentalEntity> findCompletedRentalsByOwnerId(Long ownerId);

    RentalEntity save(RentalEntity entity);

    RentalEntity findById(Long id);

    List<RentalEntity> findAll();
}
