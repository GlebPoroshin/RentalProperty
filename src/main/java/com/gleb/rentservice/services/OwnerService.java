package com.gleb.rentservice.services;

import com.gleb.rentservice.dto.RentalDTO;

import java.util.List;

public interface OwnerService {
    List<RentalDTO> getUpcomingRentalsByOwnerId(Long ownerId);

    List<RentalDTO> getCurrentRentalsByOwnerId(Long ownerId);

    List<RentalDTO> getCompletedRentalsByOwnerId(Long ownerId);

    void leaveReviewForTenant(Long rentalId, String content, Integer rating);
}
