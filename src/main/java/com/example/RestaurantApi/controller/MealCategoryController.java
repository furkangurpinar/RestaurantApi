package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.request.MealCategoryRequest;
import com.example.RestaurantApi.service.MealCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mealCategories")
public class MealCategoryController {

    private final MealCategoryService mealCategoryService;

    @GetMapping
    public ResponseEntity<List<MealCategoryDto>> getMealCategories() {
        return new ResponseEntity<>(mealCategoryService.getMealCategories(), HttpStatus.OK);
    }

    @GetMapping("/{mealCategoryId}")
    public ResponseEntity<MealCategoryDto> getMealCategory(@PathVariable int mealCategoryId) {
        return new ResponseEntity<>(mealCategoryService.getMealCategory(mealCategoryId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MealCategoryDto> createMealCategory(@RequestBody MealCategoryRequest request) {
        return new ResponseEntity<>(mealCategoryService.createMealCategory(request), HttpStatus.CREATED);
    }

    @PutMapping("/{mealCategoryId}")
    public ResponseEntity<HttpStatus> updateMealCategory(@RequestBody MealCategoryRequest mealCategoryRequest, @PathVariable int mealCategoryId) {
        mealCategoryService.updateMealCategory(mealCategoryId, mealCategoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{mealCategoryId}")
    public ResponseEntity<HttpStatus> deleteMealCategory(@PathVariable int mealCategoryId) {
        mealCategoryService.deleteMealCategory(mealCategoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
