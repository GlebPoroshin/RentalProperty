package com.gleb.rentservice.services.implementations;

import com.gleb.rentservice.dto.PropertyDTO;
import com.gleb.rentservice.enteties.PropertyEntity;
import com.gleb.rentservice.enteties.PropertyStatus;
import com.gleb.rentservice.repositories.PropertyRepository;
import com.gleb.rentservice.services.PropertyService;
import com.gleb.rentservice.services.TenantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final TenantService tenantService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, TenantService tenantService) {
        this.propertyRepository = propertyRepository;
        this.tenantService = tenantService;
    }

    @Override
    public List<PropertyDTO> getAllAvailableProperties() {
        return propertyRepository.findAllAvailableProperties().stream()
                .filter(Objects::nonNull)
                .map(property -> new PropertyDTO(
                        property.getId(),
                        property.getAddress(),
                        property.getDescription(),
                        property.getPricePerNight(),
                        property.getStatus(),
                        property.getOwner() != null ? property.getOwner().getId() : null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updatePropertyStatus(Long propertyId, PropertyStatus status) {
        if (propertyId == null || status == null) {
            throw new IllegalArgumentException("Property ID and status cannot be null");
        }

        PropertyEntity property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        property.setStatus(status);
        propertyRepository.save(property);
    }

    @Override
    public PropertyDTO getPropertyWithDynamicPrice(Long propertyId, Long tenantId) {
        if (propertyId == null || tenantId == null) {
            throw new IllegalArgumentException("Property ID and Tenant ID cannot be null");
        }

        PropertyEntity property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        double dynamicPrice = tenantService.calculateDynamicPrice(propertyId, tenantId);

        return new PropertyDTO(
                property.getId(),
                property.getAddress(),
                property.getDescription(),
                dynamicPrice,
                property.getStatus(),
                property.getOwner() != null ? property.getOwner().getId() : null);
    }
}
