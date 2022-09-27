package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(new OrderDto(1L,1L, 1L, LocalDate.now(),false,false));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok((new OrderDto(1L, 1L, 1L, LocalDate.now(), true, false)));
    }
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok().build();

    }

}
