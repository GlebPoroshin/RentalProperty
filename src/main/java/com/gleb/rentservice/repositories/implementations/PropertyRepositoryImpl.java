package com.gleb.rentservice.repositories.implementations;

import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.repositories.BaseRepository;
import com.gleb.rentservice.repositories.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class PropertyRepositoryImpl extends BaseRepository<PropertyEntity, Long> implements PropertyRepository {

    @Override
    public List<PropertyEntity> findAllAvailableProperties() {
        String jpql = "SELECT p FROM PropertyEntity p WHERE p.status = 'AVAILABLE'";
        TypedQuery<PropertyEntity> query = entityManager.createQuery(jpql, PropertyEntity.class);
        return query.getResultList();
    }

    @Override
    public List<PropertyEntity> findAllByOwnerId(Long ownerId) {
        String jpql = "SELECT p FROM PropertyEntity p WHERE p.owner.id = :ownerId";
        TypedQuery<PropertyEntity> query = entityManager.createQuery(jpql, PropertyEntity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }

    @Override
    public PropertyEntity findById(Long id) {
        return entityManager.find(PropertyEntity.class, id);
    }

    @Override
    public List<PropertyEntity> findAll() {
        return List.of();
    }

}

