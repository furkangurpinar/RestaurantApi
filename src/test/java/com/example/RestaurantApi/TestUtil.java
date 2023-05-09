package com.example.RestaurantApi;

import com.example.RestaurantApi.model.dto.*;
import com.example.RestaurantApi.model.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static List<User> getMockUsers() {
        return new ArrayList<>(Arrays.asList(
                new User(1, "Elon", "05435031446", "haho@gmail.com", "12345", null, null),
                new User(2, "Mahmut", "123548", "halay@gmail.com", "54321", null, null),
                new User(3, "Ibrahim", "963258", "domdom@gmail.com", "34216", null, null)));
    }

    public static List<UserDto> getMockUsersDtos() {
        return new ArrayList<>(Arrays.asList(
                new UserDto(1, "Elon", "05435031446", "haho@gmail.com", "12345", null, null),
                new UserDto(2, "Mahmut", "123548", "halay@gmail.com", "54321", null, null),
                new UserDto(3, "Ibrahim", "963258", "domdom@gmail.com", "34216", null, null)));
    }

    public static List<Requirement> getMockRequirements() {
        return new ArrayList<>(Arrays.asList(
                new Requirement(1, "Trabzon", "543", "Lezita", null, null),
                new Requirement(2, "Tonya", "544", "Ganita", null, null),
                new Requirement(3, "Vakfıkebir", "545", "Faroz", null, null)));
    }

    public static List<RequirementDto> getMockRequirementsDtos() {
        return new ArrayList<>(Arrays.asList(
                new RequirementDto(1, "Trabzon", "543", "Lezita", null, null),
                new RequirementDto(2, "Tonya", "544", "Ganita", null, null),
                new RequirementDto(3, "Vakfıkebir", "545", "Faroz", null, null)));
    }

    public static List<Province> getMockProvinces() {
        return new ArrayList<>(Arrays.asList(
                new Province(1, "Trabzon", null, null),
                new Province(2, "Giresun", null, null),
                new Province(3, "Samsun", null, null)));
    }

    public static List<ProvinceDto> getMockProvinceDtos() {
        return new ArrayList<>(Arrays.asList(
                new ProvinceDto(1, "Trabzon", null, null),
                new ProvinceDto(2, "Giresun", null, null),
                new ProvinceDto(3, "Samsun", null, null)));
    }

    public static List<District> getMockDistricts() {
        return new ArrayList<>(Arrays.asList(
                new District(1, "Tonya", null, null),
                new District(2, "Akçaabat", null, null),
                new District(3, "Vakfıkebir", null, null)));
    }

    public static List<DistrictDto> getMockDistrictDtos() {
        return new ArrayList<>(Arrays.asList(
                new DistrictDto(1, "Tonya", null, null),
                new DistrictDto(2, "Akçaabat", null, null),
                new DistrictDto(3, "Vakfıkebir", null, null)));
    }

    public static List<MealCategory> getMockMealCategories() {
        return new ArrayList<>(Arrays.asList(
                new MealCategory(1, "Drink", null, null),
                new MealCategory(2, "Dessert", null, null),
                new MealCategory(3, "Snack", null, null)));
    }

    public static List<MealCategoryDto> getMockMealCategoryDtos() {
        return new ArrayList<>(Arrays.asList(
                new MealCategoryDto(1, "Drink", null, null),
                new MealCategoryDto(2, "Dessert", null, null),
                new MealCategoryDto(3, "Snack", null, null)));
    }
}
