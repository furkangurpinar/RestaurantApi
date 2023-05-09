package com.example.RestaurantApi.province.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.Province.ProvinceNotFoundException;
import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.model.entity.Province;
import com.example.RestaurantApi.repository.delegate.ProvinceRepositoryDelegate;
import com.example.RestaurantApi.request.ProvinceRequest;
import com.example.RestaurantApi.service.impl.ProvinceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProvinceServiceTest {

    @Mock
    private ProvinceRepositoryDelegate provinceRepositoryDelegate;

    @InjectMocks
    private ProvinceServiceImpl provinceService;

    @Test
    void getProvinces() {
        List<Province> provinces = TestUtil.getMockProvinces();

        when(provinceRepositoryDelegate.getProvinces()).thenReturn(TestUtil.getMockProvinceDtos());

        List<ProvinceDto> response = provinceService.getProvinces();

        assertEquals(provinces.get(0).getProvinceId(), response.get(0).getProvinceId());
        assertEquals(provinces.get(1).getProvinceName(), response.get(1).getProvinceName());
        assertEquals(provinces.size(), response.size());
    }

    @Test
    void getProvince() {
        ProvinceDto provinceDto = new ProvinceDto(1, "Tonya", null, null);

        when(provinceRepositoryDelegate.getProvince(anyInt())).thenReturn(provinceDto);

        ProvinceDto result = provinceService.getProvince(1);

        assertEquals(1, result.getProvinceId());
        assertEquals("Tonya", result.getProvinceName());
    }

    @Test
    void getProvince_itShouldThrowNotFound_whenProvinceIdNotFound() {
        when(provinceRepositoryDelegate.getProvince(anyInt())).thenThrow(new ProvinceNotFoundException("Province Id Not Found - 1"));

        assertThrows(ProvinceNotFoundException.class, () -> provinceService.getProvince(1));
    }

    @Test
    void createProvince() {
        ProvinceDto testProvinceDto = new ProvinceDto(1, "Tonya", null, null);

        when(provinceRepositoryDelegate.createProvince(any(ProvinceRequest.class))).thenReturn(testProvinceDto);

        ProvinceDto responseDto = provinceService.createProvince(new ProvinceRequest());

        assertEquals(testProvinceDto, responseDto);
    }

    @Test
    void updateProvince() {
        ProvinceDto testProvinceDto = ProvinceDto.builder()
                .provinceId(1)
                .provinceName("Tonya")
                .build();

        when(provinceRepositoryDelegate.getProvince(anyInt())).thenReturn(testProvinceDto);

        ProvinceRequest request = new ProvinceRequest(testProvinceDto);

        provinceService.updateProvince(1, request);

        verify(provinceRepositoryDelegate, times(1)).updateProvince(testProvinceDto);
    }

    @Test
    void deleteProvince() {
        doNothing().when(provinceRepositoryDelegate).deleteProvince(anyInt());

        provinceService.deleteProvince(new Random().nextInt());

        verify(provinceRepositoryDelegate, times(1)).deleteProvince(anyInt());
    }

    @Test
    void deleteProvince_shouldThrowNotFoundException_WhenProvinceIdNotFound() {
        doThrow(ProvinceNotFoundException.class).when(provinceRepositoryDelegate).deleteProvince(anyInt());

        assertThrows(ProvinceNotFoundException.class, () -> provinceService.deleteProvince(new Random().nextInt()));

        verify(provinceRepositoryDelegate, times(1)).deleteProvince(anyInt());
    }
}
