package com.example.RestaurantApi.requirement.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.requirement.RequirementNotFoundException;
import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.model.entity.Requirement;
import com.example.RestaurantApi.repository.delegate.RequirementRepositoryDelegate;
import com.example.RestaurantApi.request.RequirementRequest;
import com.example.RestaurantApi.service.impl.RequirementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RequirementServiceTest {

    @Mock
    private RequirementRepositoryDelegate requirementRepositoryDelegate;

    @InjectMocks
    private RequirementServiceImpl requirementService;

    @Test
    void getRequirements() {
        List<Requirement> requirements = TestUtil.getMockRequirements();

        when(requirementRepositoryDelegate.getRequirements()).thenReturn(TestUtil.getMockRequirementsDtos());

        List<RequirementDto> response = requirementService.getRequirements();

        assertEquals(requirements.get(0).getRequirementId(), response.get(0).getRequirementId());
        assertEquals(requirements.get(1).getAddress(), response.get(1).getAddress());
        assertEquals(requirements.get(2).getPhoneNumber(), response.get(2).getPhoneNumber());
        assertEquals(requirements.get(2).getRestaurantName(), response.get(2).getRestaurantName());
        assertEquals(requirements.size(), response.size());

    }

    @Test
    void getRequirement() {
        RequirementDto requirementDto = new RequirementDto(1, "Trabzon", "543",
                "Lezita", null, null);

        when(requirementRepositoryDelegate.getRequirement(anyInt())).thenReturn(requirementDto);

        RequirementDto result = requirementService.getRequirement(1);

        assertEquals(1, result.getRequirementId());
        assertEquals("Trabzon", result.getAddress());
    }

    @Test
    void getRequirement_itShouldThrowNotFound_whenRequirementIdNotFound() {
        when(requirementRepositoryDelegate.getRequirement(anyInt()))
                .thenThrow(new RequirementNotFoundException("Requirement Id Not Found - 1"));

        assertThrows(RequirementNotFoundException.class, () -> requirementService.getRequirement(1));
    }

    @Test
    void updateUser() {

        RequirementDto testRequirementDto = RequirementDto.builder()
                .requirementId(1)
                .restaurantName("Mc")
                .phoneNumber("0543")
                .address("Trabzon")
                .build();

        when(requirementRepositoryDelegate.getRequirement(anyInt())).thenReturn(testRequirementDto);

        RequirementRequest request = new RequirementRequest(testRequirementDto);

        requirementService.updateRequirement(1, request);

        verify(requirementRepositoryDelegate, times(1)).updateRequirement(testRequirementDto);

    }

    @Test
    void deleteRequirement() {
        doNothing().when(requirementRepositoryDelegate).deleteRequirement(anyInt());

        requirementService.deleteRequirement(new Random().nextInt());

        verify(requirementRepositoryDelegate, times(1)).deleteRequirement(anyInt());

    }

    @Test
    void deleteRequirement_shouldThrowNotFoundException_WhenRequirementIdNotFound() {
        doThrow(RequirementNotFoundException.class).when(requirementRepositoryDelegate).deleteRequirement(anyInt());

        assertThrows(RequirementNotFoundException.class, () -> requirementService.deleteRequirement(new Random().nextInt()));

        verify(requirementRepositoryDelegate, times(1)).deleteRequirement(anyInt());

    }
}
