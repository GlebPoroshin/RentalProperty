package com.gleb.rentservice.config;

import com.gleb.rentservice.controllers.OwnerController;
import com.gleb.rentservice.controllers.PropertyController;
import com.gleb.rentservice.controllers.RentalController;
import com.gleb.rentservice.repositories.*;
import com.gleb.rentservice.repositories.implementations.*;
import com.gleb.rentservice.services.*;
import com.gleb.rentservice.services.implementations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OwnerRepository ownerRepository() {
        return new OwnerRepositoryImpl();
    }

    @Bean
    public PropertyRepository propertyRepository() {
        return new PropertyRepositoryImpl();
    }

    @Bean
    public RentalRepository rentalRepository() {
        return new RentalRepositoryImpl();
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new ReviewRepositoryImpl();
    }

    @Bean
    public TenantRepository tenantRepository() {
        return new TenantRepositoryImpl();
    }

    @Bean
    public OwnerService ownerService() {
        return new OwnerServiceImpl(rentalRepository(), reviewService());
    }

    @Bean
    public PropertyService propertyService() {
        return new PropertyServiceImpl(propertyRepository(), tenantService());
    }

    @Bean
    public RentalService rentalService() {
        return new RentalServiceImpl(rentalRepository(), propertyRepository(), tenantRepository());
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewServiceImpl(reviewRepository(), rentalRepository(), tenantService());
    }

    @Bean
    public TenantService tenantService() {
        return new TenantServiceImpl(tenantRepository(), reviewRepository(), propertyRepository());
    }

    @Bean
    public OwnerController ownerController() {
        return new OwnerController(ownerService());
    }

    @Bean
    public PropertyController propertyController() {
        return new PropertyController(propertyService());
    }

    @Bean
    public RentalController rentalController() {
        return new RentalController(rentalService());
    }

}
