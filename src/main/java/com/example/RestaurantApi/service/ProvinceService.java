package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.model.entity.Province;
import com.example.RestaurantApi.request.ProvinceRequest;
import com.example.RestaurantApi.request.RequirementRequest;

import java.util.List;

public interface ProvinceService {

    List<ProvinceDto> getProvinces();

    ProvinceDto getProvince(int provinceId);

    ProvinceDto createProvince(ProvinceRequest request);

    void updateProvince(int provinceId, ProvinceRequest request);

    void deleteProvince(int provinceId);
}

/*






}
 */
