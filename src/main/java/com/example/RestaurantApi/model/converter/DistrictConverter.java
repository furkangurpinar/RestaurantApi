package com.example.RestaurantApi.model.converter;

import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.model.entity.District;
import org.springframework.stereotype.Service;

@Service
public class DistrictConverter {

    public static DistrictDto convert(District from) {
        return DistrictDto.builder()
                .districtId(from.getDistrictId())
                .districtName(from.getDistrictName())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }

    public static District convert(DistrictDto from) {
        return District.builder()
                .districtId(from.getDistrictId())
                .districtName(from.getDistrictName())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }
}
