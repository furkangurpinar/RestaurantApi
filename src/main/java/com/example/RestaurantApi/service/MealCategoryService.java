package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.request.MealCategoryRequest;

import java.util.List;

public interface MealCategoryService {

    List<MealCategoryDto> getMealCategories();

    MealCategoryDto getMealCategory(int mealCategoryId);

    MealCategoryDto createMealCategory(MealCategoryRequest request);

    void updateMealCategory(int mealCategoryId, MealCategoryRequest request);

    void deleteMealCategory(int mealCategoryId);
}
