package com.example.RestaurantApi.request;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealCategoryRequest {

    MealCategoryDto mealCategoryDto;
}
