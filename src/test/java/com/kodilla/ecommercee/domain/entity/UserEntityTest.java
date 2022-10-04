package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
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

    @Test
    void testDeleteUser() {
        //Given
        UserEntity user = new UserEntity(1L, "test", "test","test","test@test.cm","test","test",LocalDate.now(),1L,1,new ArrayList<>());
        userRepository.save(user);

        //When
        userRepository.deleteById(user.getId());

        //Then
        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    void testAddingOrdersToUser() {
        //Given
        UserEntity user = new UserEntity(1L, "test", "test","test","test@test.cm","test","test",LocalDate.now(),1L,1,new ArrayList<>());
        OrderEntity order1 = new OrderEntity(null, new CartEntity(), new UserEntity(), LocalDate.now(), 200, false, LocalDate.MAX, false, LocalDate.now());
        user.getOrders().add(order1);

        //When
        userRepository.save(user);
        OrderEntity savedOrder = user.getOrders().get(0);

        //Then
        assertEquals(1, user.getOrders().size());
        assertEquals(200, savedOrder.getCost());
    }
}