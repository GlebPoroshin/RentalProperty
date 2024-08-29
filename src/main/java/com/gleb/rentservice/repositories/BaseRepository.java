package com.gleb.rentservice.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<EntityType, ID> extends Repository<EntityType, ID> {

   void save(EntityType entity);

    EntityType findById(ID id);

    List<EntityType> findAll();

}
