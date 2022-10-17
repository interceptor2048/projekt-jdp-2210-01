package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.OrderEntity;
import com.kodilla.ecommercee.errorhandlers.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository orderRepository;

    public OrderEntity getProduct(final Long productId) throws OrderNotFoundException{
        return orderRepository.findById(productId).orElseThrow(OrderNotFoundException::new);
    }

    public OrderEntity saveProduct (final OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public void deleteOrder (final Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderEntity> getAllProducts() {
        return orderRepository.findAll();
    }
}
