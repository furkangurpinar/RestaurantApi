package com.example.RestaurantApi.repository.delegate;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.request.MealCategoryRequest;

import java.util.List;

public interface MealCategoryRepositoryDelegate {

    List<MealCategoryDto> getMealCategories();

    MealCategoryDto getMealCategory(int mealCategoryId);

    MealCategoryDto createMealCategory(MealCategoryRequest request);

    void updateMealCategory(MealCategoryDto mealCategoryDto);

    void deleteMealCategory(int mealCategoryId);
}
