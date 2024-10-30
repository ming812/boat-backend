package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.GeneralResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.GeneralResponseException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public String createUser(UserDto userDto) {
        // Validate input
        validateUserDto(userDto);
        GeneralResponse generalResponse = new GeneralResponse();

        // Check for existing user
        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
//            throw new CustomException("Username already exists");
            generalResponse.setStatusCode(100);
            generalResponse.setErrorMessage("Username already exists");
            throw new GeneralResponseException(generalResponse);
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
//            throw new CustomException("Email already exists");
            generalResponse.setStatusCode(100);
            generalResponse.setErrorMessage("Email already exists");
            throw new GeneralResponseException(generalResponse);
        }
        generalResponse.setStatusCode(0);
//        User user = UserMapper.mapToUser(userDto , passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.mapToUser(userDto , userDto.getPassword());
        User savedUser = userRepository.save(user);
        try {
            generalResponse.setMessage(new ObjectMapper().writeValueAsString(UserMapper.mapToUserDto(savedUser)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return UserMapper.serializeObjectToJson(generalResponse);
    }

    // Add validation method for user DTO
    private void validateUserDto(UserDto userDto) {
        if (userDto.getUserName() == null || userDto.getUserName().isEmpty()) {
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setStatusCode(100);
            generalResponse.setErrorMessage("User name cannot be empty");
            throw new GeneralResponseException(generalResponse);
        }
        if (userDto.getEmail() == null || !EmailValidator.getInstance().isValid(userDto.getEmail())) {
            GeneralResponse generalResponse = new GeneralResponse();
            generalResponse.setStatusCode(100);
            generalResponse.setErrorMessage("Invalid email address");
            throw new GeneralResponseException(generalResponse);
        }
        // Add more validations as needed
    }


    @Override
    public UserDto getUserById(Long userId) {
       User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exist with id : " + userId));
       return UserMapper.mapToUserDto(user);
    }

    @Override
    public String loadUserByUserName(String usernameOrEmail , String password) {
        GeneralResponse generalResponse = new GeneralResponse();
        User user = userRepository.findByUserName(usernameOrEmail)
                .orElse(userRepository.findByEmail(usernameOrEmail)
                        .orElse(null));
        if(user == null || !user.getPassword().equals(password)){
            generalResponse.setStatusCode(100);
            generalResponse.setErrorMessage("User not exist or Invalid password");
        }else{
            generalResponse.setStatusCode(0);
            generalResponse.setMessage(UserMapper.serializeObjectToJson(UserMapper.mapToUserDto(user)));
//            generalResponse.setMessage("Validate");
        }
        System.out.println(generalResponse.getMessage());
        return UserMapper.serializeObjectToJson(generalResponse);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with id :" + userId)
        );
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setDateOfBirth(updatedUser.getDateOfBirth());
        user.setGender(updatedUser.getGender());
        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());

        User updateUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updateUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with id :" + userId)
        );

        userRepository.deleteById(userId);
    }
}
