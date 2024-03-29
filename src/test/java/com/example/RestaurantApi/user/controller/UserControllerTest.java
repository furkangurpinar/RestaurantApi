package com.example.RestaurantApi.user.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.converter.UserConverter;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.request.UserRequest;
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

import static org.mockito.Mockito.doNothing;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockRepository;

    @Test
    void findAll() throws Exception {
        List<User> users = TestUtil.getMockUsers();

        when(mockRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andDo(print());
    }

    @Test
    void getUser() throws Exception {
        User user = new User(1, "ibolipa", "0543", "ibo@gmail.com",
                "12345", null, null);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get(("/users/" + user.getUserId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", notNullValue()));
    }

    @Test
    void getUser_whenUserIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User ID Not Found - 61"));
    }

    @Test
    void createUser() throws Exception {
        User testUser = new User(1, "ibolipa", "0543",
                "ibo@gmail.com", "12345", null, null);
        UserRequest testRequest = new UserRequest(UserConverter.convert(testUser));

        when(mockRepository.save(testUser)).thenReturn(testUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(asJsonString(testRequest))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", notNullValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateUser() throws Exception {
        UserRequest testUser = UserRequest.builder()
                .userDto(UserDto.builder().userId(1)
                        .userName("ibolipa")
                        .userPhoneNumber("0543")
                        .userMail("ibo@gmail.com")
                        .userPassword("1234").build())
                .build();

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(TestUtil.getMockUsers().get(0)));
        when(mockRepository.save(any())).thenReturn(TestUtil.getMockUsers().get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + testUser.getUserDto().getUserId())
                        .content(asJsonString(testUser))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser_notFound() throws Exception {
        User testUser = User.builder()
                .userId(1)
                .userName("ibolipa")
                .userPhoneNumber("0543")
                .userMail("ibo@gmail.com")
                .userPassword("1234")
                .build();


        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + testUser.getUserId())
                        .content(asJsonString(testUser))
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User ID Not Found - 1"));
    }

    @Test
    void deleteUser() throws Exception {
        Optional<User> testResponse = Optional.of(new User());

        when(mockRepository.findById(anyInt())).thenReturn(testResponse);
        doNothing().when(mockRepository).delete(any(User.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUser_shouldThrowNotFoundException_WhenUserIdNotFound() throws Exception {
        doNothing().when(mockRepository).delete(any(User.class));
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/61"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User ID Not Found - 61"));
    }
}