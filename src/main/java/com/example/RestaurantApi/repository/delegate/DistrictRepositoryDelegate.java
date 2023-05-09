package com.example.RestaurantApi.repository.delegate;

import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.request.DistrictRequest;

import java.util.List;

public interface DistrictRepositoryDelegate {

    List<DistrictDto> getDistricts();

    DistrictDto getDistrict(int districtId);

    DistrictDto createDistrict(DistrictRequest request);

    void updateDistrict(DistrictDto districtDto);

    void deleteDistrict(int districtId);
}
