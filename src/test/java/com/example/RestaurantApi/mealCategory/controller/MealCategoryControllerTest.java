package com.example.RestaurantApi.mealCategory.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.converter.MealCategoryConverter;
import com.example.RestaurantApi.model.dto.MealCategoryDto;
import com.example.RestaurantApi.model.entity.MealCategory;
import com.example.RestaurantApi.repository.MealCategoryRepository;
import com.example.RestaurantApi.request.MealCategoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MealCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealCategoryRepository mockRepository;

    @Test
    void findAll() throws Exception {
        List<MealCategory> mealCategories = TestUtil.getMockMealCategories();


        when(mockRepository.findAll()).thenReturn(mealCategories);

        mockMvc.perform(MockMvcRequestBuilders.get("/mealCategories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mealCategories.size()))
                .andDo(print());
    }

    @Test
    void getMealCategory() throws Exception {
        MealCategory mealCategory = new MealCategory(1, "Dessert", null, null);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(mealCategory));

        mockMvc.perform(MockMvcRequestBuilders.get(("/mealCategories/" + mealCategory.getMealCategoryId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mealCategoryId", notNullValue()));
    }

    @Test
    void getMealCategory_whenMealCategoryIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/mealCategories/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("MealCategory ID Not Found - 61"));
    }

    @Test
    void createMealCategory() throws Exception {
        MealCategory testMealCategory = new MealCategory(1, "Dessert", null, null);

        MealCategoryRequest testRequest = new MealCategoryRequest(MealCategoryConverter.convert(testMealCategory));

        when(mockRepository.save(testMealCategory)).thenReturn(testMealCategory);

        mockMvc.perform(MockMvcRequestBuilders.post("/mealCategories")
                        .content(asJsonString(testRequest))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mealCategoryId", notNullValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateMealCategory() throws Exception {
        MealCategoryRequest testMealCategory = MealCategoryRequest.builder()
                .mealCategoryDto(MealCategoryDto.builder()
                        .mealCategoryId(1)
                        .mealCategory("Dessert").build()).build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getMockMealCategories().get(0)));
        when(mockRepository.save(any())).thenReturn(TestUtil.getMockMealCategories().get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/mealCategories/" + testMealCategory.getMealCategoryDto().getMealCategoryId())
                        .content(asJsonString(testMealCategory))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void updateMealCategory_notFound() throws Exception {
        MealCategory testMealCategory = MealCategory.builder()
                .mealCategoryId(1)
                .mealCategory("Dessert")
                .build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/mealCategories/" + testMealCategory.getMealCategoryId())
                        .content(asJsonString(testMealCategory))
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("MealCategory ID Not Found - 1"));
    }

    @Test
    void deleteMealCategory() throws Exception {
        Optional<MealCategory> testResponse = Optional.of(new MealCategory());

        when(mockRepository.findById(anyInt())).thenReturn(testResponse);
        doNothing().when(mockRepository).delete(any(MealCategory.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/mealCategories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteMealCategory_shouldThrowNotFoundException_WhenMealCategoryIdNotFound() throws Exception {
        doNothing().when(mockRepository).delete(any(MealCategory.class));
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/mealCategories/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("MealCategory ID Not Found - 61"));
    }
}
