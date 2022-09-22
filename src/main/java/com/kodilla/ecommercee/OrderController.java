package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(Long orderId) {
        return new OrderDto();
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(Long orderId) {

    }
    @PostMapping(value = "{orderId}")
    public OrderDto updateOrder(Long orderId) {
        return new OrderDto();
    }
    @PutMapping
    public void createOrder(OrderDto orderDto){

    }

}
