package com.gleb.rentservice.repositories.implementations;

import com.gleb.rentservice.enteties.OwnerEntity;
import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.RentalEntity;
import com.gleb.rentservice.repositories.BaseRepository;
import com.gleb.rentservice.repositories.OwnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerRepositoryImpl extends BaseRepository<OwnerEntity, Long> implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OwnerEntity findOwnerById(Long ownerId) {
        return repository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Owner not found"));
    }

    @Override
    public List<PropertyEntity> findPropertiesByOwnerId(Long ownerId) {
        String jpql = "SELECT p FROM PropertyEntity p WHERE p.owner.id = :ownerId";
        TypedQuery<PropertyEntity> query = entityManager.createQuery(jpql, PropertyEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public List<RentalEntity> findRentalsByOwnerId(Long ownerId) {
        String jpql = "SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId";
        TypedQuery<RentalEntity> query = entityManager.createQuery(jpql, RentalEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
