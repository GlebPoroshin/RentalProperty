package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.dto.RentalDTO;
import com.gleb.rentservice.repositories.RentalRepository;
import com.gleb.rentservice.services.OwnerService;
import com.gleb.rentservice.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final RentalRepository rentalRepository;
    private final ReviewService reviewService;

    @Autowired
    public OwnerServiceImpl(RentalRepository rentalRepository, ReviewService reviewService) {
        this.rentalRepository = rentalRepository;
        this.reviewService = reviewService;
    }

    @Override
    public List<RentalDTO> getUpcomingRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findUpcomingRentalsByOwnerId(ownerId).stream()
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty().getId(),
                        rental.getTenant().getId(),
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalDTO> getCurrentRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findCurrentRentalsByOwnerId(ownerId).stream()
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty().getId(),
                        rental.getTenant().getId(),
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalDTO> getCompletedRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findCompletedRentalsByOwnerId(ownerId).stream()
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty().getId(),
                        rental.getTenant().getId(),
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void leaveReviewForTenant(Long rentalId, String content, Integer rating) {
        reviewService.leaveReviewForTenant(rentalId, content, rating);
    }
}
