package com.gleb.rentservice.repositories;

import com.gleb.rentservice.enteties.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {}
