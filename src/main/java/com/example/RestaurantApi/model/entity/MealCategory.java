package com.example.RestaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealCategoryId;

    @Column
    private String mealCategory;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;
}

