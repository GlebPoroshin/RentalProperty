package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    @Query("SELECT p FROM PropertyEntity p WHERE p.status = 'AVAILABLE'")
    List<PropertyEntity> findAllAvailableProperties();

    @Query("SELECT p FROM PropertyEntity p WHERE p.owner.id = :ownerId")
    List<PropertyEntity> findAllByOwnerId(@Param("ownerId") Long ownerId);
}
