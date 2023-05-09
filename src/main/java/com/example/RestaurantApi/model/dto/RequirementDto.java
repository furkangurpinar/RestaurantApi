package com.example.RestaurantApi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequirementDto {

    private int requirementId;

    private String address;

    private String phoneNumber;

    private String restaurantName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
