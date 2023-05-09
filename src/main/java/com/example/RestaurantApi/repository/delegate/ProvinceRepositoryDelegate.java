package com.example.RestaurantApi.repository.delegate;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.request.ProvinceRequest;

import java.util.List;

public interface ProvinceRepositoryDelegate {

    List<ProvinceDto> getProvinces();

    ProvinceDto getProvince(int provinceId);

    ProvinceDto createProvince(ProvinceRequest request);

    void updateProvince(ProvinceDto provinceDto);

    void deleteProvince(int provinceId);
}
