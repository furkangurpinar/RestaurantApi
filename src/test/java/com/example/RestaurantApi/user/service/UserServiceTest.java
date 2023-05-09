package com.example.RestaurantApi.user.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.user.UserNotFoundException;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.delegate.UserRepositoryDelegate;
import com.example.RestaurantApi.request.UserRequest;
import com.example.RestaurantApi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepositoryDelegate userRepositoryDelegate;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUsers() {
        List<User> users = TestUtil.getMockUsers();

        when(userRepositoryDelegate.getUsers()).thenReturn(TestUtil.getMockUsersDtos());

        List<UserDto> response = userService.getUsers();

        assertEquals(users.get(0).getUserId(), response.get(0).getUserId());
        assertEquals(users.get(1).getUserMail(), response.get(1).getUserMail());
        assertEquals(users.get(2).getUserPassword(), response.get(2).getUserPassword());
        assertEquals(users.get(1).getUserPhoneNumber(), response.get(1).getUserPhoneNumber());
        assertEquals(users.size(), response.size());
    }

    @Test
    void getUser() {
        UserDto userDto = new UserDto(1, "ibolipa", "0543",
                "ibo@gmail.com", "12345", null, null);

        when(userRepositoryDelegate.getUser(anyInt())).thenReturn(userDto);

        UserDto result = userService.getUser(1);

        assertEquals(1, result.getUserId());
        assertEquals("ibolipa", result.getUserName());
    }

    @Test
    void getUser_itShouldThrowNotFound_whenUserIdNotFound() {
        when(userRepositoryDelegate.getUser(anyInt())).thenThrow(new UserNotFoundException("User Id Not Found - 1"));

        assertThrows(UserNotFoundException.class, () -> userService.getUser(1));
    }

    @Test
    void createUser() {
        UserDto testUserDto = new UserDto(1, "ibolipa", "0543",
                "ibo@gmail.com", "12345", null, null);

        when(userRepositoryDelegate.createUser(any(UserRequest.class))).thenReturn(testUserDto);

        UserDto responseDto = userService.createUser(new UserRequest());

        assertEquals(testUserDto, responseDto);
    }

    @Test
    void updateUser() {
        UserDto testUserDto = UserDto.builder()
                .userId(1)
                .userName("ibolipa")
                .userPhoneNumber("0543")
                .userMail("ibo@gmail.com")
                .build();

        when(userRepositoryDelegate.getUser(anyInt())).thenReturn(testUserDto);

        UserRequest request = new UserRequest(testUserDto);

        userService.updateUser(1, request);

        verify(userRepositoryDelegate, times(1)).updateUser(testUserDto);
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepositoryDelegate).deleteUser(anyInt());

        userService.deleteUser(new Random().nextInt());

        verify(userRepositoryDelegate, times(1)).deleteUser(anyInt());
    }

    @Test
    void deleteUser_shouldThrowNotFoundException_WhenUserIdNotFound() {
        doThrow(UserNotFoundException.class).when(userRepositoryDelegate).deleteUser(anyInt());

        assertThrows(UserNotFoundException.class, () ->userService.deleteUser(new Random().nextInt()));

        verify(userRepositoryDelegate, times(1)).deleteUser(anyInt());
    }
}