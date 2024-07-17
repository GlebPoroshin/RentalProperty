package com.gleb.rentservice.repositories;

import jakarta.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public abstract class BaseRepository<EntityType, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void save(EntityType entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public EntityType findById(Class<EntityType> entityTypeClass, ID id) {
        String jpql = "SELECT e FROM " + entityTypeClass.getSimpleName() +
                " e WHERE e.id = :id";
        TypedQuery<EntityType> query = entityManager.createQuery(jpql, entityTypeClass)
                .setParameter("id", id);

        List<EntityType> results = query.getResultList();
        if (results.isEmpty()) {
            throw new EntityNotFoundException(entityTypeClass.getSimpleName() + " with id " + id + " not found");
        } else {
            return results.get(0);
        }
    }

    @Transactional
    public List<EntityType> findAll(Class<EntityType> entityTypeClass) {
        String jpql = "SELECT e FROM " + entityTypeClass.getSimpleName() + " e";
        TypedQuery<EntityType> query = entityManager.createQuery(jpql, entityTypeClass);
        List<EntityType> results = query.getResultList();

        if (results.isEmpty()) {
            throw new NoResultException("No entities found for class " + entityTypeClass.getName());
        }

        return results;
    }
}
