package com.example.RestaurantApi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDto {

    private int provinceId;

    private String provinceName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
