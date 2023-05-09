package com.example.RestaurantApi.model.converter;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.model.entity.MealCategory;
import org.springframework.stereotype.Service;

@Service
public class MealCategoryConverter {

    public static MealCategoryDto convert(MealCategory from) {
        return MealCategoryDto.builder()
                .mealCategoryId(from.getMealCategoryId())
                .mealCategory(from.getMealCategory())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }

    public static MealCategory convert(MealCategoryDto from) {
        return MealCategory.builder()
                .mealCategoryId(from.getMealCategoryId())
                .mealCategory(from.getMealCategory())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }
}
