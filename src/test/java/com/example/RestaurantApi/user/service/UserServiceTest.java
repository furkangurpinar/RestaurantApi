package com.example.RestaurantApi.user.service;
import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.dto.converter.UserDtoConverter;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository mockRepository;

    @Spy
    private UserDtoConverter dtoConverter ;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        userService = new UserServiceImpl(mockRepository, dtoConverter);
    }

    @Test
     void getUsers(){

        List<User> users = TestUtil.getMockUsers();

        when(mockRepository.findAll()).thenReturn(users);

        List<UserDto> response =userService.getUsers();

        assertEquals(users.get(0).getUserId(), response.get(0).getUserId());
        assertEquals(users.get(1).getUserMail(), response.get(1).getUserMail());
        assertEquals(users.get(2).getUserPassword(), response.get(2).getUserPassword());
        assertEquals(users.get(1).getUserPhoneNumber(), response.get(1).getUserPhoneNumber());
        assertEquals(users.size(),response.size());
    }
}