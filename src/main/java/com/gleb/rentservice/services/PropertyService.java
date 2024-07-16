package com.gleb.rentservice.services;

import com.gleb.rentservice.dto.PropertyDTO;
import com.gleb.rentservice.enteties.PropertyStatus;

import java.util.List;

public interface PropertyService {
    List<PropertyDTO> getAllAvailableProperties();

    void updatePropertyStatus(Long propertyId, PropertyStatus status);

    PropertyDTO getPropertyWithDynamicPrice(Long propertyId, Long tenantId);
}