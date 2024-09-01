package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.PropertyStatus;
import com.gleb.rentservice.enteties.RentalEntity;
import com.gleb.rentservice.enteties.TenantEntity;
import com.gleb.rentservice.repositories.PropertyRepository;
import com.gleb.rentservice.repositories.RentalRepository;
import com.gleb.rentservice.repositories.TenantRepository;
import com.gleb.rentservice.services.RentalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, PropertyRepository propertyRepository, TenantRepository tenantRepository) {
        this.rentalRepository = rentalRepository;
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    @Transactional
    public void createRental(Long tenantId, Long propertyId, LocalDateTime startDate, LocalDateTime endDate) {
        if (tenantId == null || propertyId == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        PropertyEntity property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        RentalEntity rental = new RentalEntity(startDate, endDate, property, tenant, null);
        rentalRepository.save(rental);

        property.setStatus(PropertyStatus.AWAITING_PAYMENT);
        propertyRepository.save(property);
    }

    @Override
    @Transactional
    public void cancelRental(Long rentalId, String reason) {
        if (rentalId == null || reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Invalid rentalId or reason");
        }

        RentalEntity rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        rental.setCancellationReason(reason);
        rentalRepository.save(rental);

        PropertyEntity property = rental.getProperty();
        property.setStatus(PropertyStatus.AVAILABLE);
        propertyRepository.save(property);
    }

    @Override
    @Transactional
    public void confirmPayment(Long rentalId) {
        if (rentalId == null) {
            throw new IllegalArgumentException("Invalid rentalId");
        }

        RentalEntity rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        PropertyEntity property = rental.getProperty();
        property.setStatus(PropertyStatus.RENTED);
        propertyRepository.save(property);
    }

    @Override
    @Scheduled(fixedRate = 9000000)
    @Transactional
    public void checkAndUpdatePropertyStatus() {
        LocalDateTime now = LocalDateTime.now();
        List<RentalEntity> rentals = rentalRepository.findAll().stream()
                .filter(rental -> rental.getEndDate().isBefore(now) && rental.getProperty().getStatus() == PropertyStatus.RENTED)
                .toList();

        for (RentalEntity rental : rentals) {
            PropertyEntity property = rental.getProperty();
            property.setStatus(PropertyStatus.AVAILABLE);
            propertyRepository.save(property);
        }
    }
}
