package com.example.RestaurantApi.model.converter;

import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.model.entity.Requirement;
import org.springframework.stereotype.Service;

@Service
public class RequirementConverter {

    public static RequirementDto convert(Requirement from) {
        return RequirementDto.builder()
                .requirementId(from.getRequirementId())
                .address(from.getAddress())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .phoneNumber(from.getPhoneNumber())
                .restaurantName(from.getRestaurantName())
                .build();
    }

    public static Requirement convert(RequirementDto from) {
        return Requirement.builder()
                .requirementId(from.getRequirementId())
                .address(from.getAddress())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .phoneNumber(from.getPhoneNumber())
                .restaurantName(from.getRestaurantName())
                .build();
    }
}
