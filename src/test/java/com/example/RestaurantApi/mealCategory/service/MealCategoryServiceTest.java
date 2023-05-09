package com.example.RestaurantApi.mealCategory.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.mealCategory.MealCategoryNotFoundException;
import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.model.entity.MealCategory;
import com.example.RestaurantApi.repository.delegate.MealCategoryRepositoryDelegate;
import com.example.RestaurantApi.request.MealCategoryRequest;
import com.example.RestaurantApi.service.impl.MealCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MealCategoryServiceTest {

    @Mock
    private MealCategoryRepositoryDelegate mealCategoryRepositoryDelegate;

    @InjectMocks
    private MealCategoryServiceImpl mealCategoryService;

    @Test
    void getMealCategories() {
        List<MealCategory> mealCategories = TestUtil.getMockMealCategories();

        when(mealCategoryRepositoryDelegate.getMealCategories()).thenReturn(TestUtil.getMockMealCategoryDtos());

        List<MealCategoryDto> response = mealCategoryService.getMealCategories();

        assertEquals(mealCategories.get(0).getMealCategoryId(), response.get(0).getMealCategoryId());
        assertEquals(mealCategories.get(1).getMealCategory(), response.get(1).getMealCategory());
        assertEquals(mealCategories.size(), response.size());
    }

    @Test
    void getMealCategory() {
        MealCategoryDto mealCategoryDto = new MealCategoryDto(1, "Dessert", null, null);

        when(mealCategoryRepositoryDelegate.getMealCategory(anyInt())).thenReturn(mealCategoryDto);

        MealCategoryDto result = mealCategoryService.getMealCategory(1);

        assertEquals(1, result.getMealCategoryId());
        assertEquals("Dessert", result.getMealCategory());
    }

    @Test
    void getMealCategory_itShouldThrowNotFound_whenMealCategoryIdNotFound() {
        when(mealCategoryRepositoryDelegate.getMealCategory(anyInt())).thenThrow(new MealCategoryNotFoundException("MealCategory Id Not Found - 1"));

        assertThrows(MealCategoryNotFoundException.class, () -> mealCategoryService.getMealCategory(1));
    }

    @Test
    void createMealCategory() {
        MealCategoryDto testMealCategoryDto = new MealCategoryDto(1, "Dessert", null, null);

        when(mealCategoryRepositoryDelegate.createMealCategory(any(MealCategoryRequest.class))).thenReturn(testMealCategoryDto);

        MealCategoryDto responseDto = mealCategoryService.createMealCategory(new MealCategoryRequest());

        assertEquals(testMealCategoryDto, responseDto);
    }

    @Test
    void updateMealCategory() {
        MealCategoryDto testMealCategoryDto = MealCategoryDto.builder()
                .mealCategoryId(1)
                .mealCategory("Dessert")
                .build();

        when(mealCategoryRepositoryDelegate.getMealCategory(anyInt())).thenReturn(testMealCategoryDto);

        MealCategoryRequest request = new MealCategoryRequest(testMealCategoryDto);

        mealCategoryService.updateMealCategory(1, request);

        verify(mealCategoryRepositoryDelegate, times(1)).updateMealCategory(testMealCategoryDto);
    }

    @Test
    void deleteMealCategory() {
        doNothing().when(mealCategoryRepositoryDelegate).deleteMealCategory(anyInt());

        mealCategoryService.deleteMealCategory(new Random().nextInt());

        verify(mealCategoryRepositoryDelegate, times(1)).deleteMealCategory(anyInt());
    }

    @Test
    void deleteMealCategory_shouldThrowNotFoundException_WhenMealCategoryIdNotFound() {
        doThrow(MealCategoryNotFoundException.class).when(mealCategoryRepositoryDelegate).deleteMealCategory(anyInt());

        assertThrows(MealCategoryNotFoundException.class, () -> mealCategoryService.deleteMealCategory(new Random().nextInt()));

        verify(mealCategoryRepositoryDelegate, times(1)).deleteMealCategory(anyInt());
    }
}
