package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.repository.delegate.ProvinceRepositoryDelegate;
import com.example.RestaurantApi.request.ProvinceRequest;
import com.example.RestaurantApi.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepositoryDelegate provinceRepositoryDelegate;

    @Override
    public List<ProvinceDto> getProvinces() {
        return provinceRepositoryDelegate.getProvinces();
    }

    @Override
    public ProvinceDto getProvince(int provinceId) {
        return provinceRepositoryDelegate.getProvince(provinceId);
    }

    @Override
    public ProvinceDto createProvince(ProvinceRequest request) {
        return provinceRepositoryDelegate.createProvince(request);
    }

    @Override
    public void updateProvince(int provinceId, ProvinceRequest request) {
        ProvinceDto province = provinceRepositoryDelegate.getProvince(provinceId);

        final ProvinceDto requestDto = request.getProvinceDto();

        province.setProvinceName(requestDto.getProvinceName());

        provinceRepositoryDelegate.updateProvince(province);
    }

    @Override
    public void deleteProvince(int provinceId) {
        provinceRepositoryDelegate.deleteProvince(provinceId);

    }
}
