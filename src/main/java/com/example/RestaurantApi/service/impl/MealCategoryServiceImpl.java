package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.repository.delegate.MealCategoryRepositoryDelegate;
import com.example.RestaurantApi.request.MealCategoryRequest;
import com.example.RestaurantApi.service.MealCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealCategoryServiceImpl implements MealCategoryService {

    private final MealCategoryRepositoryDelegate mealCategoryRepositoryDelegate;

    @Override
    public List<MealCategoryDto> getMealCategories() {
        return mealCategoryRepositoryDelegate.getMealCategories();
    }

    @Override
    public MealCategoryDto getMealCategory(int mealCategoryId) {
        return mealCategoryRepositoryDelegate.getMealCategory(mealCategoryId);
    }

    @Override
    public MealCategoryDto createMealCategory(MealCategoryRequest request) {
        return mealCategoryRepositoryDelegate.createMealCategory(request);
    }

    @Override
    public void updateMealCategory(int mealCategoryId, MealCategoryRequest request) {
        MealCategoryDto mealCategory = mealCategoryRepositoryDelegate.getMealCategory(mealCategoryId);

        final MealCategoryDto requestDto = request.getMealCategoryDto();

        mealCategory.setMealCategory(requestDto.getMealCategory());

        mealCategoryRepositoryDelegate.updateMealCategory(mealCategory);

    }

    @Override
    public void deleteMealCategory(int mealCategoryId) {
        mealCategoryRepositoryDelegate.deleteMealCategory(mealCategoryId);

    }
}
