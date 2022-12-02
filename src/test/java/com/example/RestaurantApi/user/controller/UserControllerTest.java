package com.example.RestaurantApi.user.controller;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
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

import static org.hamcrest.Matchers.notNullValue;
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
        User user = new User(1, "ibolipa", "0543", "ibo@gmail.com", "12345");

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
}