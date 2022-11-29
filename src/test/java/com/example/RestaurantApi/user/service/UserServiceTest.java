package com.example.RestaurantApi.user.service;
import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.exception.user.UserNotFoundException;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.repository.delegate.impl.UserRepositoryDelegateImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockRepository;

    @InjectMocks
    private UserRepositoryDelegateImpl userRepositoryDelegate;

    @Test
     void getUsers(){

        List<User> users = TestUtil.getMockUsers();

        when(mockRepository.findAll()).thenReturn(users);

        List<UserDto> response =userRepositoryDelegate.getUsers();//userService silindi

        assertEquals(users.get(0).getUserId(), response.get(0).getUserId());
        assertEquals(users.get(1).getUserMail(), response.get(1).getUserMail());
        assertEquals(users.get(2).getUserPassword(), response.get(2).getUserPassword());
        assertEquals(users.get(1).getUserPhoneNumber(), response.get(1).getUserPhoneNumber());
        assertEquals(users.size(),response.size());
    }
    @Test
    void getUser(){
        User user= new User(1,"ibolipa","0543","ibo@gmail.com","12345");

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(user));

        UserDto result =userRepositoryDelegate.getUser(1);

        assertEquals(result.getUserId(),1);
        assertEquals("ibolipa",result.getUserName());
    }

    @Test
    void getUser_itShouldThrowNotFound_whenUserIdNotFound(){
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userRepositoryDelegate.getUser(anyInt()));
    }
}