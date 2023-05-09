package com.example.RestaurantApi.province.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.converter.ProvinceConverter;
import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.model.entity.Province;
import com.example.RestaurantApi.repository.ProvinceRepository;
import com.example.RestaurantApi.request.ProvinceRequest;
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
class ProvinceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProvinceRepository mockRepository;

    @Test
    void findAll() throws Exception {
        List<Province> provinces = TestUtil.getMockProvinces();


        when(mockRepository.findAll()).thenReturn(provinces);

        mockMvc.perform(MockMvcRequestBuilders.get("/provinces"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(provinces.size()))
                .andDo(print());
    }

    @Test
    void getProvince() throws Exception {
        Province province = new Province(1, "Trabzon", null, null);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(province));

        mockMvc.perform(MockMvcRequestBuilders.get(("/provinces/" + province.getProvinceId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.provinceId", notNullValue()));
    }

    @Test
    void getProvince_whenProvinceIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/provinces/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Province ID Not Found - 61"));
    }

    @Test
    void createProvince() throws Exception {
        Province testProvince = new Province(1, "Trabzon", null, null);

        ProvinceRequest testRequest = new ProvinceRequest(ProvinceConverter.convert(testProvince));

        when(mockRepository.save(testProvince)).thenReturn(testProvince);

        mockMvc.perform(MockMvcRequestBuilders.post("/provinces")
                        .content(asJsonString(testRequest))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.provinceId", notNullValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateProvince() throws Exception {
        ProvinceRequest testProvince = ProvinceRequest.builder()
                .provinceDto(ProvinceDto.builder()
                        .provinceId(1)
                        .provinceName("Trabzon").build()).build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getMockProvinces().get(0)));
        when(mockRepository.save(any())).thenReturn(TestUtil.getMockProvinces().get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/provinces/" + testProvince.getProvinceDto().getProvinceId())
                        .content(asJsonString(testProvince))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void updateProvince_notFound() throws Exception {
        Province testProvince = Province.builder()
                .provinceId(1)
                .provinceName("Tonya")
                .build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/provinces/" + testProvince.getProvinceId())
                        .content(asJsonString(testProvince))
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Province ID Not Found - 1"));
    }

    @Test
    void deleteProvince() throws Exception {
        Optional<Province> testResponse = Optional.of(new Province());

        when(mockRepository.findById(anyInt())).thenReturn(testResponse);
        doNothing().when(mockRepository).delete(any(Province.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/provinces/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProvince_shouldThrowNotFoundException_WhenProvinceIdNotFound() throws Exception {
        doNothing().when(mockRepository).delete(any(Province.class));
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/provinces/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Province ID Not Found - 61"));
    }
}
