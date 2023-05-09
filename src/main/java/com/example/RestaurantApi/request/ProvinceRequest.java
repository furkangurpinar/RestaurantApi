package com.example.RestaurantApi.request;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceRequest {

    ProvinceDto provinceDto;
}
