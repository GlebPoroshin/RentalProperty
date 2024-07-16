package com.gleb.rentservice.controllers;

import com.gleb.rentservice.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/create")
    public void createRental(@RequestParam Long tenantId, @RequestParam Long propertyId,
                                               @RequestParam String startDate, @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        rentalService.createRental(tenantId, propertyId, start, end);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelRental(@RequestParam Long rentalId, @RequestParam String reason) {
        rentalService.cancelRental(rentalId, reason);
        return ResponseEntity.ok("Rental canceled successfully");
    }

    @PostMapping("/confirmPayment")
    public ResponseEntity<String> confirmPayment(@RequestParam Long rentalId) {
        rentalService.confirmPayment(rentalId);
        return ResponseEntity.ok("Payment confirmed successfully");
    }

    @GetMapping("/checkStatus")
    public ResponseEntity<String> checkAndUpdatePropertyStatus() {
        rentalService.checkAndUpdatePropertyStatus();
        return ResponseEntity.ok("Property statuses updated successfully");
    }
}
