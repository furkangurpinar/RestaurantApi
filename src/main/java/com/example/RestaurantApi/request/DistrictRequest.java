package com.example.RestaurantApi.request;

import com.example.RestaurantApi.model.dto.DistrictDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictRequest {

    DistrictDto districtDto;
}
