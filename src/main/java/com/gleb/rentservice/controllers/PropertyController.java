package com.gleb.rentservice.controllers;

import com.gleb.rentservice.dto.PropertyDTO;
import com.gleb.rentservice.enteties.PropertyStatus;
import com.gleb.rentservice.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<PropertyDTO>> getAllAvailableProperties() {
        List<PropertyDTO> properties = propertyService.getAllAvailableProperties();
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updatePropertyStatus(@RequestParam Long propertyId, @RequestParam PropertyStatus status) {
        propertyService.updatePropertyStatus(propertyId, status);
        return ResponseEntity.ok("Property status updated successfully");
    }

    @GetMapping("/{propertyId}/dynamicPrice")
    public ResponseEntity<String> getPropertyWithDynamicPrice(@PathVariable Long propertyId, @RequestParam Long tenantId) {
        PropertyDTO property = propertyService.getPropertyWithDynamicPrice(propertyId, tenantId);
        return ResponseEntity.ok("Price per night for property on " + property.getAddress() + " is " + property.getPricePerNight() + "$");
    }
}
