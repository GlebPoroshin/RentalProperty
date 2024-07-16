package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.enteties.TenantEntity;
import com.gleb.rentservice.repositories.PropertyRepository;
import com.gleb.rentservice.repositories.ReviewRepository;
import com.gleb.rentservice.repositories.TenantRepository;
import com.gleb.rentservice.services.TenantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository, ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.tenantRepository = tenantRepository;
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public double calculateAverageRating(Long tenantId) {
        List<ReviewEntity> reviews = reviewRepository.findByTenantId(tenantId);
        double averageRating = reviews.stream().mapToDouble(ReviewEntity::getRating).average().orElse(0.0);

        TenantEntity tenant = tenantRepository.findById(tenantId);
        if (tenant == null) throw new EntityNotFoundException("Tenant not found");
        tenant.setAverageRating(averageRating);
        tenantRepository.save(tenant);

        return averageRating;
    }

    @Override
    public double getDiscountCoefficient(double averageRating) {
        if (averageRating >= 5.0) {
            return 0.8;
        } else if (averageRating >= 4.0) {
            return 0.9;
        } else {
            return 1.0;
        }
    }

    @Override
    public double calculateDynamicPrice(Long propertyId, Long tenantId) {
        PropertyEntity property = propertyRepository.findById(propertyId);
        if (property == null) throw new EntityNotFoundException("Property not found");
        TenantEntity tenant = tenantRepository.findById(tenantId);
        if (tenant == null) throw new EntityNotFoundException("Tenant not found");
        double basePrice = property.getPricePerNight();
        double discountCoefficient = getDiscountCoefficient(tenant.getAverageRating());
        return basePrice * discountCoefficient;
    }

    @Override
    public void updateTenantRating(Long tenantId) {
        TenantEntity tenant = tenantRepository.findById(tenantId);
        tenant.setAverageRating(calculateAverageRating(tenantId));
    }
}
