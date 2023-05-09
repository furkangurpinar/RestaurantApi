package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.mealCategory.MealCategoryNotFoundException;
import com.example.RestaurantApi.model.converter.MealCategoryConverter;
import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.model.entity.MealCategory;
import com.example.RestaurantApi.repository.MealCategoryRepository;
import com.example.RestaurantApi.repository.delegate.MealCategoryRepositoryDelegate;
import com.example.RestaurantApi.request.MealCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealCategoryRepositoryDelegateImpl implements MealCategoryRepositoryDelegate {

    private final MealCategoryRepository mealCategoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MealCategoryDto> getMealCategories() {
        return mealCategoryRepository.findAll()
                .stream()
                .map(MealCategoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public MealCategoryDto getMealCategory(int mealCategoryId) {
        Optional<MealCategory> response = mealCategoryRepository.findById(mealCategoryId);
        if (response.isEmpty()) {
            throw new MealCategoryNotFoundException("MealCategory ID Not Found - " + mealCategoryId);
        }
        return MealCategoryConverter.convert(response.get());
    }

    @Transactional
    @Override
    public MealCategoryDto createMealCategory(MealCategoryRequest request) {
        MealCategory mealCategory = MealCategoryConverter.convert(request.getMealCategoryDto());
        mealCategory.setCreateDate(LocalDateTime.now());
        mealCategoryRepository.save(mealCategory);
        return MealCategoryConverter.convert(mealCategory);
    }

    @Transactional
    @Override
    public void updateMealCategory(MealCategoryDto mealCategoryDto) {
        MealCategory mealCategory = MealCategoryConverter.convert(mealCategoryDto);
        mealCategory.setUpdateDate(LocalDateTime.now());
        mealCategoryRepository.save(mealCategory);

    }

    @Transactional
    @Override
    public void deleteMealCategory(int mealCategoryId) {

        Optional<MealCategory> response = mealCategoryRepository.findById(mealCategoryId);
        if (response.isEmpty()) {
            throw new MealCategoryNotFoundException("MealCategory ID Not Found - " + mealCategoryId);
        }
        mealCategoryRepository.delete(response.get());


    }
}
