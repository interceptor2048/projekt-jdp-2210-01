package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.errorhandlers.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserDbService {
    @NotNull
    private final UserRepository userRepository;

    public UserEntity findCartById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUser(Long userId) throws UserNotFoundException {
        Supplier<UserNotFoundException> supplier = () -> new UserNotFoundException(
                "User with given id doesn't exist");
        return userRepository.findById(userId).orElseThrow(supplier);
    }

    public UserEntity saveUser(final UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        userRepository.delete(getUser(userId));
    }
}

