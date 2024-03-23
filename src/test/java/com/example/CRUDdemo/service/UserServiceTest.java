package com.example.CRUDdemo.service;

import com.example.CRUDdemo.controllers.UserController;
import com.example.CRUDdemo.model.User;
import com.example.CRUDdemo.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private User user;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
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