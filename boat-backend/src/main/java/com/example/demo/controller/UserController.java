package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.GeneralResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    //This restful api for add User
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        String savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser , HttpStatus.CREATED);
    }

    //This restful api for get User
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    //This restful api for get all User
    @GetMapping
    public ResponseEntity<String> getAllUser(){
        List<UserDto> users = userService.getAllUser();
//        return ResponseEntity.ok(users);
        return ResponseEntity.ok("getAllUser success");
    }

    //This restful api for update user
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId ,
                                              @RequestBody UserDto updatedUser){
        UserDto userDto = userService.updateUser(userId , updatedUser);
        return ResponseEntity.ok(userDto);
    }

    //This restful api for delete user
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userDto){
        return ResponseEntity.ok(userService.loadUserByUserName(userDto.getUserName() == null ? userDto.getEmail() : userDto.getUserName() , userDto.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful.");
    }

}

@Getter
@Setter
class UserLoginRequest {
    private String userName;
    private String password;
    private String email;

    // getters and setters
}
