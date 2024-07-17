package com.gleb.rentservice.repositories.implementations;

import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.repositories.BaseRepository;
import com.gleb.rentservice.repositories.ReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends BaseRepository<ReviewEntity, Long> implements ReviewRepository {

    @Override
    public List<ReviewEntity> findByTenantId(Long tenantId) {
        String jpql = "SELECT r FROM ReviewEntity r WHERE r.tenant.id = :tenantId";
        TypedQuery<ReviewEntity> query = entityManager.createQuery(jpql, ReviewEntity.class);
        query.setParameter("tenantId", tenantId);
        return query.getResultList();
    }

    @Override
    public List<ReviewEntity> findByPropertyId(Long propertyId) {
        String jpql = "SELECT r FROM ReviewEntity r WHERE r.property.id = :propertyId";
        TypedQuery<ReviewEntity> query = entityManager.createQuery(jpql, ReviewEntity.class);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    @Override
    public ReviewEntity findById(Long id) {
        return super.findById(ReviewEntity.class, id);
    }

    @Override
    public List<ReviewEntity> findAll() {
        return super.findAll(ReviewEntity.class);
    }
}
