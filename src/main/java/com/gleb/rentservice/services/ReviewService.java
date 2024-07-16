package com.gleb.rentservice.services;

import com.gleb.rentservice.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByTenantId(Long tenantId);

    List<ReviewDTO> getReviewsByPropertyId(Long propertyId);

    void leaveReviewForTenant(Long rentalId, String content, Integer rating);
}
