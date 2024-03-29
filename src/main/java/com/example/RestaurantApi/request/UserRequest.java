package com.example.RestaurantApi.request;

import com.example.RestaurantApi.model.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    UserDto userDto;
}
