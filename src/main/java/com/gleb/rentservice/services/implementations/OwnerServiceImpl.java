package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.dto.RentalDTO;
import com.gleb.rentservice.repositories.RentalRepository;
import com.gleb.rentservice.services.OwnerService;
import com.gleb.rentservice.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        return rentalRepository.findUpcomingRentalsByOwnerId(ownerId)
                .stream()
                .filter(Objects::nonNull)
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty() != null ? rental.getProperty().getId() : null,
                        rental.getTenant() != null ? rental.getTenant().getId() : null,
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalDTO> getCurrentRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findCurrentRentalsByOwnerId(ownerId)
                .stream()
                .filter(Objects::nonNull)
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty() != null ? rental.getProperty().getId() : null,
                        rental.getTenant() != null ? rental.getTenant().getId() : null,
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalDTO> getCompletedRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findCompletedRentalsByOwnerId(ownerId)
                .stream()
                .filter(Objects::nonNull)
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getProperty() != null ? rental.getProperty().getId() : null,
                        rental.getTenant() != null ? rental.getTenant().getId() : null,
                        rental.getCancellationReason()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void leaveReviewForTenant(Long rentalId, String content, Integer rating) {
        if (rentalId == null || content == null || content.isEmpty() || rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Invalid review data provided.");
        }
        reviewService.leaveReviewForTenant(rentalId, content, rating);
    }
}
