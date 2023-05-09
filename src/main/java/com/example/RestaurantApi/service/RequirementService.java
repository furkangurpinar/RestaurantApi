package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.request.RequirementRequest;

import java.util.List;

public interface RequirementService {

    List<RequirementDto> getRequirements();

    RequirementDto getRequirement(int requirementId);

    RequirementDto createRequirement(RequirementRequest request);

    void updateRequirement(int requirementId , RequirementRequest request);

    void deleteRequirement(int requirementId);
}
