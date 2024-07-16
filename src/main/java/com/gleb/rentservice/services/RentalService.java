package com.gleb.rentservice.services;


import java.time.LocalDateTime;

public interface RentalService {
    void createRental(Long tenantId, Long propertyId, LocalDateTime startDate, LocalDateTime endDate);

    void cancelRental(Long rentalId, String reason);

    void confirmPayment(Long rentalId);

    void checkAndUpdatePropertyStatus();
}