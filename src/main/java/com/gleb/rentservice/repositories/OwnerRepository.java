package com.gleb.rentservice.repositories;


import com.gleb.rentservice.enteties.OwnerEntity;
import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.RentalEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OwnerRepository {

    List<PropertyEntity> findPropertiesByOwnerId(Long ownerId);

    List<RentalEntity> findRentalsByOwnerId(Long ownerId);

    void save(OwnerEntity entity);

    OwnerEntity findById(Long id);

    List<OwnerEntity> findAll();
}
