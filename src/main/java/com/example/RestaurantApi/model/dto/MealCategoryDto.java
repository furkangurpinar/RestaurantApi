package com.example.RestaurantApi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealCategoryDto {

    private int mealCategoryId;

    private String mealCategory;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
