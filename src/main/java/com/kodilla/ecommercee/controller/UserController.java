package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserInputDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.errorhandlers.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserDbService userDbService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userMapper.mapToUsersListDto(userDbService.getAllUsers()));
    }

    @GetMapping({"userId"})
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        UserEntity user = userDbService.getUser(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserInputDto userInputDto) {
        UserEntity user = userDbService.saveUser(userMapper.mapToUserEntity(userInputDto));
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserInputDto userInputDto) {
        return createUser(userInputDto);
    }

    @DeleteMapping({"userId"})
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userDbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/block_user")
    public ResponseEntity<String> blockUserById(@RequestParam Long id){
        return ResponseEntity.ok("User with requested ID was blocked");
    }

    @GetMapping("/get_key")
    public ResponseEntity<Integer> getUserKey(@RequestParam String login,@RequestParam String password){
        return ResponseEntity.ok(1);
    }
}
