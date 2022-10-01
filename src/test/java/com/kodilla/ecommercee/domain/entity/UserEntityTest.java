package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserEntity() {
        //Given
        UserEntity user = new UserEntity(1L, "test", "test","test","test@test.cm","test","test",LocalDate.now(),1L,1,new ArrayList<>());

        //When
        userRepository.save(user);

        //Then
        Long id = user.getId();
        Optional<UserEntity> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());
        assertEquals(user.getFirstName(), readUser.get().getFirstName());
    }
}