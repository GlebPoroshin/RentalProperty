package com.gleb.rentservice.services;

public interface TenantService {

    double calculateAverageRating(Long tenantId);

    double getDiscountCoefficient(double averageRating);

    double calculateDynamicPrice(Long propertyId, Long tenantId);

    void updateTenantRating(Long tenantId);
}
