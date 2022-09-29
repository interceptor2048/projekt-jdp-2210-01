package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.dto.UserInputDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @PostMapping("/create_user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserInputDto userInputDto){
        return ResponseEntity.ok(new UserDto(1L, userInputDto.getLogin(), 1,666));
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
