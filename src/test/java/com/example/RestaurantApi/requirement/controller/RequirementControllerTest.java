package com.example.RestaurantApi.requirement.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.model.converter.RequirementConverter;
import com.example.RestaurantApi.model.entity.Requirement;
import com.example.RestaurantApi.repository.RequirementRepository;
import com.example.RestaurantApi.request.RequirementRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RequirementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequirementRepository mockRepository;

    @Test
    void findAll() throws Exception {
        List<Requirement> requirements = TestUtil.getMockRequirements();

        when(mockRepository.findAll()).thenReturn(requirements);

        mockMvc.perform(MockMvcRequestBuilders.get("/requirements"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(requirements.size()))
                .andDo(print());
    }

    @Test
    void getRequirement() throws Exception {
        Requirement requirement = new Requirement(
                2, "Tonya", "544", "Ganita", null, null);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(requirement));

        mockMvc.perform(MockMvcRequestBuilders.get(("/requirements/" + requirement.getRequirementId())))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requirementId", notNullValue()));
    }

    @Test
    void getRequirement_whenRequirementIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/requirements/61"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Requirement ID Not Found - 61"));
    }

    @Test
    void createRequirement() throws Exception {
        Requirement testRequirement = new Requirement(
                2, "Tonya", "544", "Ganita", null, null);

        RequirementRequest testRequest = new RequirementRequest(RequirementConverter.convert(testRequirement));

        when(mockRepository.save(testRequirement)).thenReturn(testRequirement);

        mockMvc.perform(MockMvcRequestBuilders.post("/requirements")
                        .content(asJsonString(testRequest))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requirementId", notNullValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateRequirement() throws Exception {
        RequirementRequest testRequirement = RequirementRequest.builder()
                .requirementDto(RequirementDto.builder().requirementId(1)
                        .restaurantName("Has")
                        .phoneNumber("543")
                        .address("Tonya")
                        .build()).build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getMockRequirements().get(0)));
        when(mockRepository.save(any())).thenReturn(TestUtil.getMockRequirements().get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/requirements/" + testRequirement.getRequirementDto()
                                .getRequirementId()) //.getUserDto().getUserId())
                        .content(asJsonString(testRequirement))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void updateRequirement_notFound() throws Exception {
        Requirement testRequirement = Requirement.builder()
                .requirementId(1)
                .address("Trabzon")
                .phoneNumber("123")
                .restaurantName("Haho")
                .build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/requirements/" + testRequirement.getRequirementId())
                        .content(asJsonString(testRequirement))
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Requirement ID Not Found - 1"));
    }

    @Test
    void deleteRequirement() throws Exception {
        Optional<Requirement> testResponse = Optional.of(new Requirement());

        when(mockRepository.findById(anyInt())).thenReturn(testResponse);
        doNothing().when(mockRepository).delete(any(Requirement.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/requirements/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteRequirement_shouldThrowNotFoundException_WhenRequirementIdNotFound() throws Exception {
        doNothing().when(mockRepository).delete(any(Requirement.class));
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/requirements/61"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Requirement ID Not Found - 61"));
    }
}

