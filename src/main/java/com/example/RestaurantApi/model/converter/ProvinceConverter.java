package com.example.RestaurantApi.model.converter;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.model.entity.Province;
import org.springframework.stereotype.Service;

@Service
public class ProvinceConverter {

    public static ProvinceDto convert(Province from) {
        return ProvinceDto.builder()
                .provinceId(from.getProvinceId())
                .provinceName(from.getProvinceName())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }

    public static Province convert(ProvinceDto from) {
        return Province.builder()
                .provinceId(from.getProvinceId())
                .provinceName(from.getProvinceName())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }
}
