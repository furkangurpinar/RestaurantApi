package com.example.RestaurantApi.district.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.district.DistrictNotFoundException;
import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.model.entity.District;
import com.example.RestaurantApi.repository.delegate.DistrictRepositoryDelegate;
import com.example.RestaurantApi.request.DistrictRequest;
import com.example.RestaurantApi.service.impl.DistrictServiceImpl;
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
class DistrictServiceTest {

    @Mock
    private DistrictRepositoryDelegate districtRepositoryDelegate;

    @InjectMocks
    private DistrictServiceImpl districtService;

    @Test
    void getDistricts() {
        List<District> districts = TestUtil.getMockDistricts();

        when(districtRepositoryDelegate.getDistricts()).thenReturn(TestUtil.getMockDistrictDtos());

        List<DistrictDto> response = districtService.getDistricts();

        assertEquals(districts.get(0).getDistrictId(), response.get(0).getDistrictId());
        assertEquals(districts.get(1).getDistrictName(), response.get(1).getDistrictName());
        assertEquals(districts.size(), response.size());
    }

    @Test
    void getDistrict() {
        DistrictDto districtDto = new DistrictDto(1, "Tonya",  null, null);

        when(districtRepositoryDelegate.getDistrict(anyInt())).thenReturn(districtDto);

        DistrictDto result = districtService.getDistrict(1);

        assertEquals(1, result.getDistrictId());
        assertEquals("Tonya", result.getDistrictName());
    }

    @Test
    void getDistrict_itShouldThrowNotFound_whenDistrictIdNotFound() {
        when(districtRepositoryDelegate.getDistrict(anyInt())).thenThrow(new DistrictNotFoundException("District Id Not Found - 1"));

        assertThrows(DistrictNotFoundException.class, () -> districtService.getDistrict(1));
    }

    @Test
    void createDistrict() {
        DistrictDto testDistrictDto = new DistrictDto(1, "Tonya", null, null);

        when(districtRepositoryDelegate.createDistrict(any(DistrictRequest.class))).thenReturn(testDistrictDto);

        DistrictDto responseDto = districtService.createDistrict(new DistrictRequest());

        assertEquals(testDistrictDto, responseDto);
    }

    @Test
    void updateDistrict() {
        DistrictDto testDistrictDto = DistrictDto.builder()
                .districtId(1)
                .districtName("Tonya")
                .build();

        when(districtRepositoryDelegate.getDistrict(anyInt())).thenReturn(testDistrictDto);

        DistrictRequest request = new DistrictRequest(testDistrictDto);

        districtService.updateDistrict(1, request);

        verify(districtRepositoryDelegate, times(1)).updateDistrict(testDistrictDto);
    }

    @Test
    void deleteDistrict() {
        doNothing().when(districtRepositoryDelegate).deleteDistrict(anyInt());

        districtService.deleteDistrict(new Random().nextInt());

        verify(districtRepositoryDelegate, times(1)).deleteDistrict(anyInt());
    }

    @Test
    void deleteDistrict_shouldThrowNotFoundException_WhenDistrictIdNotFound() {
        doThrow(DistrictNotFoundException.class).when(districtRepositoryDelegate).deleteDistrict(anyInt());

        assertThrows(DistrictNotFoundException.class, () -> districtService.deleteDistrict(new Random().nextInt()));

        verify(districtRepositoryDelegate, times(1)).deleteDistrict(anyInt());
    }

}