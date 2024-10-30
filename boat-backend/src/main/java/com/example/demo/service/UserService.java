package com.example.demo.service;

import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    String createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    String loadUserByUserName(String usernameOrEmail , String password);

    List<UserDto> getAllUser();

    UserDto updateUser(Long userId , UserDto updatedUser);

    void deleteUser(Long userId);
}
