package com.example.RestaurantApi.repository;

import com.example.RestaurantApi.model.entity.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealCategoryRepository extends JpaRepository<MealCategory, Integer> {
}
