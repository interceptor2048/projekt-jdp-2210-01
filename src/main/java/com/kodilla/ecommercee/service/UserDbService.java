package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.errorhandlers.CartNotFoundException;
import com.kodilla.ecommercee.errorhandlers.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbService {

    @NotNull
    private final UserRepository userRepository;

    public UserEntity findUserById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
