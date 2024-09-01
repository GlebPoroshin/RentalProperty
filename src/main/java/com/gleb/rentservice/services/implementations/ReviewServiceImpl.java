package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.dto.ReviewDTO;
import com.gleb.rentservice.enteties.RentalEntity;
import com.gleb.rentservice.enteties.ReviewEntity;
import com.gleb.rentservice.repositories.RentalRepository;
import com.gleb.rentservice.repositories.ReviewRepository;
import com.gleb.rentservice.services.ReviewService;
import com.gleb.rentservice.services.TenantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RentalRepository rentalRepository;
    private final TenantService tenantService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, RentalRepository rentalRepository, TenantService tenantService) {
        this.reviewRepository = reviewRepository;
        this.rentalRepository = rentalRepository;
        this.tenantService = tenantService;
    }

    @Override
    public List<ReviewDTO> getReviewsByTenantId(Long tenantId) {
        return reviewRepository.findByTenantId(tenantId).stream()
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getContent(),
                        review.getRating(),
                        review.getProperty().getId(),
                        review.getTenant().getId(),
                        review.getRental().getId(),
                        review.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByPropertyId(Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId).stream()
                .filter(Objects::nonNull)
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getContent(),
                        review.getRating(),
                        review.getProperty().getId(),
                        review.getTenant().getId(),
                        review.getRental().getId(),
                        review.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void leaveReviewForTenant(Long rentalId, String content, Integer rating) {
        if (rentalId == null || content == null || content.isEmpty() || rating == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        RentalEntity rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        ReviewEntity review = new ReviewEntity(content, rating, rental.getProperty(), rental.getTenant(), rental);
        reviewRepository.save(review);

        tenantService.updateTenantRating(rating.longValue());
    }
}
