package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public class UserMapper extends CommonMapper {

    public static UserDto mapToUserDto(User user) {

        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getUserType()
        );
    }


    public static User mapToUser(UserDto userDto, String password) {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                password,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getDateOfBirth(),
                userDto.getGender(),
                userDto.getUserType()
        );
    }

}
