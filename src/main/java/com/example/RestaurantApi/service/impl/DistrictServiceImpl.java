package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.repository.delegate.DistrictRepositoryDelegate;
import com.example.RestaurantApi.request.DistrictRequest;
import com.example.RestaurantApi.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepositoryDelegate districtRepositoryDelegate;

    @Override
    public List<DistrictDto> getDistricts() {
        return districtRepositoryDelegate.getDistricts();
    }

    @Override
    public DistrictDto getDistrict(int districtId) {
        return districtRepositoryDelegate.getDistrict(districtId);
    }

    @Override
    public DistrictDto createDistrict(DistrictRequest request) {
        return districtRepositoryDelegate.createDistrict(request);
    }

    @Override
    public void updateDistrict(int districtId, DistrictRequest request) {

        DistrictDto district = districtRepositoryDelegate.getDistrict(districtId);

        final DistrictDto requestDto = request.getDistrictDto();

        district.setDistrictName(requestDto.getDistrictName());

        districtRepositoryDelegate.updateDistrict(district);

    }

    @Override
    public void deleteDistrict(int districtId) {
        districtRepositoryDelegate.deleteDistrict(districtId);

    }
}
