package com.kodilla.ecommercee.asia;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEmptyCart(@RequestBody CartDto cartDto){
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsFromCart(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping(value = "/add_product")
    public ResponseEntity<ProductDto> addProductToCart(@RequestParam Long productId, @RequestParam Long cardId){
        return ResponseEntity.ok(new ProductDto(1L,"book","black",10.5,2L));
    }

    @DeleteMapping(value = "/remove_product")
    public ResponseEntity<Void> removeProductFromCart(@RequestParam Long productId, @RequestParam Long cardId){
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create_order")
    public ResponseEntity<OrderDto> createOrderFromCart(@RequestParam Long cartId){
        return ResponseEntity.ok(new OrderDto(1L, 1L, 1L, LocalDate.now(), true, false));
    }
}
