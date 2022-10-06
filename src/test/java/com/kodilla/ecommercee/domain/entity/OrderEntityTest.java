package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderEntityTest {

    @Autowired
    private OrderRepository orderRepository;
    private UserEntity userEntity;
    private CartEntity cartEntity;

    @Test
    void testOrderEntitySave() {

        //Given
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());

        //When
        orderRepository.save(orderEntity);

        //Then
        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());

        //Cleanup
        orderRepository.deleteById(id);
    }

    @Test
    void testOrderEntityDelete() {

        //Given
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        orderRepository.save(orderEntity);
        long id = orderEntity.getId();
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());

        //Then
        orderRepository.deleteById(orderEntity.getId());

        //When
        Optional<OrderEntity> order1 = orderRepository.findById(id);
        assertFalse(order1.isPresent());

    }
    @Test
    void testOrderEntityFindAll() {

        //Given
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        OrderEntity orderEntity1 = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        orderRepository.save(orderEntity);
        orderRepository.save(orderEntity1);

        //When
        List<OrderEntity> orderList = orderRepository.findAll();

        //Then
        assertEquals(2, orderList.size());

        //Cleanup
        orderRepository.deleteAll();

    }
    @Test
    void testOrderEntityFindById() {
        //Given
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        OrderEntity orderEntity1 = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),25,false,LocalDate.now(),false,LocalDate.now());
        orderRepository.save(orderEntity);
        orderRepository.save(orderEntity1);
        long id = orderEntity.getId();

        //When
        List<OrderEntity> orderList = orderRepository.findAll();
        Optional<OrderEntity> findByIdOrder = orderRepository.findById(id);

        //Then
        assertTrue(findByIdOrder.isPresent());
        assertEquals(22, findByIdOrder.get().getCost());

        //Cleanup
        orderRepository.deleteAll();

    }
    @Test
    void testOrderEntityUpdate() {

        //Given
        OrderEntity orderEntity = new OrderEntity(null, cartEntity, userEntity, LocalDate.now(),22,false,LocalDate.now(),false,LocalDate.now());
        orderRepository.save(orderEntity);

        //When
        long id = orderEntity.getId();
        orderEntity.setCost(50);

        //Then
        orderRepository.save(orderEntity);
        Optional<OrderEntity> order = orderRepository.findById(id);
        assertTrue(order.isPresent());
        assertEquals(50, order.get().getCost());

        //Cleanup
        orderRepository.deleteById(id);
    }
}