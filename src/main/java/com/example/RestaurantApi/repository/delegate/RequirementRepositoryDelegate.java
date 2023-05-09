package com.example.RestaurantApi.repository.delegate;

import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.request.RequirementRequest;

import java.util.List;

public interface RequirementRepositoryDelegate {
    List<RequirementDto> getRequirements();

    RequirementDto getRequirement(int requirementId);

    RequirementDto createRequirement(RequirementRequest request);

    void updateRequirement(RequirementDto requirementDto);

    void deleteRequirement(int requirementId);

}

