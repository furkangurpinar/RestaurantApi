package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.requirement.RequirementNotFoundException;
import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.model.converter.RequirementConverter;
import com.example.RestaurantApi.model.entity.Requirement;
import com.example.RestaurantApi.repository.RequirementRepository;
import com.example.RestaurantApi.repository.delegate.RequirementRepositoryDelegate;
import com.example.RestaurantApi.request.RequirementRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RequirementRepositoryDelegateImpl implements RequirementRepositoryDelegate {

    private final RequirementRepository requirementRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RequirementDto> getRequirements() {
        return requirementRepository.findAll()
                .stream()
                .map(RequirementConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RequirementDto getRequirement(int requirementId) {
        Optional<Requirement> response = requirementRepository.findById(requirementId);
        if (response.isEmpty()) {
            throw new RequirementNotFoundException("Requirement ID Not Found - " + requirementId);
        }
        return RequirementConverter.convert(response.get());

    }

    @Transactional
    @Override
    public RequirementDto createRequirement(RequirementRequest request) {
        Requirement requirement = RequirementConverter.convert(request.getRequirementDto());
        requirement.setCreateDate(LocalDateTime.now());
        requirementRepository.save(requirement);
        return RequirementConverter.convert(requirement);

    }

    @Transactional
    @Override
    public void updateRequirement(RequirementDto requirementDto) {
        Requirement requirement = RequirementConverter.convert(requirementDto);
        requirement.setUpdateDate(LocalDateTime.now());
        requirementRepository.save(requirement);

    }

    @Transactional
    @Override
    public void deleteRequirement(int requirementId) {
        Optional<Requirement> response = requirementRepository.findById(requirementId);
        if (response.isEmpty()) {
            throw new RequirementNotFoundException("Requirement ID Not Found - " + requirementId);
        }
        requirementRepository.delete(response.get());

    }
}
