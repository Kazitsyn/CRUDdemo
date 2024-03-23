package com.example.CRUDdemo.service;

import com.example.CRUDdemo.model.User;
import com.example.CRUDdemo.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceIntegrationTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private User user;
    @Autowired
    private UserService userService;
    @Test
    public void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);

        boolean result = userService.saveUser(user).equals(user);

        verify(userRepository, times(1)).save(user);
        assert(result);
    }



    @Test
    public void testUpdateUser() {

        doNothing().when(userRepository).updateById(user);
        userRepository.updateById(user);
        verify(userRepository, times(1)).updateById(user);

    }

    @Test
    public void testDeleteUser() {

        doNothing().when(userRepository).deleteById(1);

        userService.deleteById(1);

        verify(userRepository, times(1)).deleteById(1);

    }


    @Test
    @DisplayName("Service test findAll")
    void userServiceFiendAll(){
        List<User> findAll = userService.findAll();
        verify(userRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Service test saveUser")
    void userServiceSaveUser(){
        userService.saveUser(user);
        verify(userRepository,times(1)).save(user);
    }
}
