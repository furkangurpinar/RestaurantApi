package com.example.RestaurantApi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {

    private int districtId;

    private String districtName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
