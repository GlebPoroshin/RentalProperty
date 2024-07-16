package com.gleb.rentservice.repositories.implementations;

import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.enteties.TenantEntity;
import com.gleb.rentservice.repositories.BaseRepository;
import com.gleb.rentservice.repositories.TenantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantRepositoryImpl extends BaseRepository<TenantEntity, Long> implements TenantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TenantEntity findTenantById(Long tenantId) {
        return entityManager.find(TenantEntity.class, tenantId);
    }

    @Override
    public List<ReviewEntity> findReviewsByTenantId(Long tenantId) {
        String jpql = "SELECT r FROM ReviewEntity r WHERE r.tenant.id = :tenantId";
        TypedQuery<ReviewEntity> query = entityManager.createQuery(jpql, ReviewEntity.class);
        query.setParameter("tenantId", tenantId);
        return query.getResultList();
    }

    @Override
    public void updateTenantAverageRating(Long tenantId, double averageRating) {
        TenantEntity tenant = entityManager.find(TenantEntity.class, tenantId);
        if (tenant != null) {
            tenant.setAverageRating(averageRating);
            entityManager.merge(tenant);
        } else {
            throw new EntityNotFoundException("Tenant not found");
        }
    }
}
