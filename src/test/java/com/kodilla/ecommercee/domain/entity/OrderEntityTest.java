package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderEntityTest {

    @Autowired
    private OrderRepository orderRepository;

    private UserEntity userEntity;
    private CartEntity cartEntity;

    public OrderEntityTest() {
    }

    @Test
    void testOrderEntitySave() {
        //Given
        OrderEntity orderEntity = new OrderEntity(1L, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());

        //When
        orderRepository.save(orderEntity);

        //Then
        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());

        //CleanUp
        orderRepository.deleteById(id);

    }

    @Test
    void testOrderEntityDelete() {
        //Given
        OrderEntity orderEntity = new OrderEntity(2L, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        orderRepository.save(orderEntity);

        //When
        orderRepository.deleteById(orderEntity.getId());

        //Then
        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertFalse(order.isPresent());

    }
}