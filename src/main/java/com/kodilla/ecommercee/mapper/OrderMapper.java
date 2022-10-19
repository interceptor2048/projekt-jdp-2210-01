package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.entity.OrderEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import com.kodilla.ecommercee.errorhandlers.CartNotFoundException;
import com.kodilla.ecommercee.errorhandlers.GroupNotFoundException;
import com.kodilla.ecommercee.errorhandlers.UserNotFoundException;
import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private CartDbService cartDbService;
    private UserDbService userDbService;

    public OrderEntity mapToOrder (final OrderDto orderDto) {
        try {
            return new OrderEntity(
                    orderDto.getOrderId(),
                    cartDbService.findCartById(orderDto.getOrderId()),
                    userDbService.findCartById(orderDto.getOrderId()),
                    orderDto.getOrderCreationDate(),
                    orderDto.getOrderCost(),
                    orderDto.isOrderIsClosed(),
                    orderDto.getOrderClosingDate(),
                    orderDto.isOrderIsDelivered(),
                    orderDto.getOrderDeliveryDate()
            );
        } catch (CartNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderDto mapToOrderDto(final OrderEntity order) {
        return new OrderDto(
                order.getId(),
                order.getCartId().getId(),
                order.getUserId().getId(),
                order.getCreationDate(),
                order.getCost(),
                order.isClosed(),
                order.getClosingDate(),
                order.isDelivered(),
                order.getDeliveryDate()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<OrderEntity> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}