package com.gleb.rentservice.repositories.implementations;

import com.gleb.rentservice.enteties.RentalEntity;
import com.gleb.rentservice.repositories.BaseRepository;
import com.gleb.rentservice.repositories.RentalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepositoryImpl extends BaseRepository<RentalEntity, Long> implements RentalRepository {

    @Override
    public List<RentalEntity> findUpcomingRentalsByOwnerId(Long ownerId) {
        String jpql = "SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate > CURRENT_TIMESTAMP";
        TypedQuery<RentalEntity> query = entityManager.createQuery(jpql, RentalEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public List<RentalEntity> findCurrentRentalsByOwnerId(Long ownerId) {
        String jpql = "SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.startDate <= CURRENT_TIMESTAMP AND r.endDate >= CURRENT_TIMESTAMP";
        TypedQuery<RentalEntity> query = entityManager.createQuery(jpql, RentalEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public List<RentalEntity> findCompletedRentalsByOwnerId(Long ownerId) {
        String jpql = "SELECT r FROM RentalEntity r WHERE r.property.owner.id = :ownerId AND r.endDate <= CURRENT_TIMESTAMP";
        TypedQuery<RentalEntity> query = entityManager.createQuery(jpql, RentalEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public RentalEntity findById(Long id) {
        return entityManager.find(RentalEntity.class, id);
    }

    @Override
    public List<RentalEntity> findAll() {
        return List.of();
    }

}
