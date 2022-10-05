package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OrderEntityRelationTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartEntityRepository cartEntityRepository;
    @Autowired
    private UserRepository userRepository;



    @Test
    void testOrderEntityRelationSave() {

        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(), LocalDate.now(), 100);
        UserEntity userEntity = new UserEntity(null, "John", "Doe", "NY", "email", "Lol", "password", LocalDate.now(), 1L, 2,new ArrayList<>());
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        //When
        cartEntityRepository.save(cartEntity);
        userRepository.save(userEntity);
        orderRepository.save(orderEntity);

        //Then
        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
      // System.out.println(order);

        assertTrue(order.isPresent());

        //Cleanup
        orderRepository.deleteById(id);
        userRepository.deleteById(userEntity.getId());
        cartEntityRepository.deleteById(cartEntity.getId());
    }

    @Test
    void testOrderEntityRelationDelete() {

        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(), LocalDate.now(), 100);
        UserEntity userEntity = new UserEntity(null, "John", "Doe", "NY", "email", "Lol", "password", LocalDate.now(), 1L, 2,new ArrayList<>());
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        cartEntityRepository.save(cartEntity);
        userRepository.save(userEntity);
        orderRepository.save(orderEntity);

        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());

        //When
        orderRepository.deleteById(id);

        //Then
        Optional<OrderEntity> order1 = orderRepository.findById(id);
        assertFalse(order1.isPresent());

        //Cleanup
        userRepository.deleteById(userEntity.getId());
        cartEntityRepository.deleteById(cartEntity.getId());
    }

    @Test
    void testOrderEntityRelationFind() {

        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(), LocalDate.now(), 100);
        UserEntity userEntity = new UserEntity();
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        cartEntityRepository.save(cartEntity);
        userRepository.save(userEntity);
        orderRepository.save(orderEntity);
        //When

        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());

        UserEntity userFromOrder = order.get().getUserId();
        CartEntity cartFromUser = order.get().getCartId();

        assertEquals(userEntity, userFromOrder);
        assertEquals(cartEntity, cartFromUser);

        //Then
        //Cleanup
        orderRepository.deleteById(id);
        userRepository.deleteById(userEntity.getId());
        cartEntityRepository.deleteById(cartEntity.getId());
    }

}
