package com.example.RestaurantApi.district.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.converter.DistrictConverter;
import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.model.entity.District;
import com.example.RestaurantApi.repository.DistrictRepository;
import com.example.RestaurantApi.request.DistrictRequest;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DistrictControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DistrictRepository mockRepository;

    @Test
    void findAll() throws Exception {
        List<District> districts = TestUtil.getMockDistricts();

        when(mockRepository.findAll()).thenReturn(districts);

        mockMvc.perform(MockMvcRequestBuilders.get("/districts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(districts.size()))
                .andDo(print());
    }

    @Test
    void getDistrict() throws Exception {
        District district = new District(1, "Tonya", null, null);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(district));

        mockMvc.perform(MockMvcRequestBuilders.get(("/districts/" + district.getDistrictId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.districtId", notNullValue()));
    }

    @Test
    void getDistrict_wheDistrictIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/districts/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("District ID Not Found - 61"));
    }

    @Test
    void createDistrict() throws Exception {
        District testDistrict = new District(1, "Tonya", null, null);
        DistrictRequest testRequest = new DistrictRequest(DistrictConverter.convert(testDistrict));

        when(mockRepository.save(testDistrict)).thenReturn(testDistrict);

        mockMvc.perform(MockMvcRequestBuilders.post("/districts")
                        .content(asJsonString(testRequest))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.districtId", notNullValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateDistrict() throws Exception {
        DistrictRequest testDistrict = DistrictRequest.builder()
                .districtDto(DistrictDto.builder()
                        .districtId(1)
                        .districtName("Tonya")
                        .build()).build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getMockDistricts().get(0)));
        when(mockRepository.save(any())).thenReturn(TestUtil.getMockDistricts().get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/districts/" + testDistrict.getDistrictDto().getDistrictId())
                        .content(asJsonString(testDistrict))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void updateDistrict_notFound() throws Exception {
        District testDistrict = District.builder()
                .districtId(1)
                .districtName("Tonya")
                .build();


        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/districts/" + testDistrict.getDistrictId())
                        .content(asJsonString(testDistrict))
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("District ID Not Found - 1"));
    }

    @Test
    void deleteDistrict() throws Exception {
        Optional<District> testResponse = Optional.of(new District());

        when(mockRepository.findById(anyInt())).thenReturn(testResponse);
        doNothing().when(mockRepository).delete(any(District.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/districts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteDistrict_shouldThrowNotFoundException_WhenDistrictIdNotFound() throws Exception {
        doNothing().when(mockRepository).delete(any(District.class));
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/districts/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("District ID Not Found - 61"));
    }
}