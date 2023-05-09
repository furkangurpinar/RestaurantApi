package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.repository.delegate.RequirementRepositoryDelegate;
import com.example.RestaurantApi.request.RequirementRequest;
import com.example.RestaurantApi.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepositoryDelegate requirementRepositoryDelegate;

    @Override
    public List<RequirementDto> getRequirements() {
        return requirementRepositoryDelegate.getRequirements();
    }

    @Override
    public RequirementDto getRequirement(int requirementId) {
        return requirementRepositoryDelegate.getRequirement(requirementId);
    }

    @Override
    public RequirementDto createRequirement(RequirementRequest request) {
        return requirementRepositoryDelegate.createRequirement(request);
    }

    @Override
    public void updateRequirement(int requirementId, RequirementRequest request) {
        RequirementDto requirement = requirementRepositoryDelegate.getRequirement(requirementId);

        final RequirementDto requestDto= request.getRequirementDto();

        requirement.setRestaurantName(requestDto.getRestaurantName());
        requirement.setPhoneNumber(requestDto.getPhoneNumber());
        requirement.setAddress(requestDto.getAddress());

        requirementRepositoryDelegate.updateRequirement(requirement);

    }

    @Override
    public void deleteRequirement(int requirementId) {
        requirementRepositoryDelegate.deleteRequirement(requirementId);

    }
}
