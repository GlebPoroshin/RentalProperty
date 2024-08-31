package com.gleb.rentservice.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<EntityType, ID> extends Repository<EntityType, ID> {

   void save(EntityType entity);

    Optional<EntityType> findById(ID id);

    List<EntityType> findAll();

}
