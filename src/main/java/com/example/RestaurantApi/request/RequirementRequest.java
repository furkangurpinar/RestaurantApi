package com.example.RestaurantApi.request;

import com.example.RestaurantApi.model.dto.RequirementDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementRequest {

    RequirementDto requirementDto;

}
