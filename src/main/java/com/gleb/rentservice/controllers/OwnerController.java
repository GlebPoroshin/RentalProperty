package com.gleb.rentservice.controllers;

import com.gleb.rentservice.dto.RentalDTO;
import com.gleb.rentservice.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerId}/rentals/upcoming")
    public ResponseEntity<List<RentalDTO>> getUpcomingRentals(@PathVariable Long ownerId) {
        List<RentalDTO> rentals = ownerService.getUpcomingRentalsByOwnerId(ownerId);
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{ownerId}/rentals/current")
    public ResponseEntity<List<RentalDTO>> getCurrentRentals(@PathVariable Long ownerId) {
        List<RentalDTO> rentals = ownerService.getCurrentRentalsByOwnerId(ownerId);
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{ownerId}/rentals/completed")
    public ResponseEntity<List<RentalDTO>> getCompletedRentals(@PathVariable Long ownerId) {
        List<RentalDTO> rentals = ownerService.getCompletedRentalsByOwnerId(ownerId);
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("/rentals/{rentalId}/reviews")
    public ResponseEntity<String> leaveReviewForTenant(@PathVariable Long rentalId,
                                                       @RequestParam String content,
                                                       @RequestParam Integer rating) {
        ownerService.leaveReviewForTenant(rentalId, content, rating);
        return ResponseEntity.ok("Review submitted successfully");
    }
}
