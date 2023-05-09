package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.request.DistrictRequest;

import java.util.List;

public interface DistrictService {

    List<DistrictDto> getDistricts();

    DistrictDto getDistrict(int districtId);

    DistrictDto createDistrict(DistrictRequest request);

    void updateDistrict(int  districtId ,DistrictRequest request);

    void deleteDistrict(int districtId);
}
