package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartResponseDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.OrderEntity;
import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductsInCartMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductsInCartsExternalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {


    private final CartMapper cartMapper;
    private final CartDbService cartDbService;
    private final ProductsInCartMapper productsInCartMapper;
    private final ProductsInCartsExternalDbService productsInCartsExternalDbService;


    //do w podmiany w finalnej wersji//
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEmptyCart(@RequestBody  CartDto cartDto){
        CartEntity cartEntity=cartMapper.mapToNewCart(cartDto);
        cartDbService.saveCart(cartEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get_products_from_cart")
    public ResponseEntity<CartResponseDto> getProductsFromCart(@RequestParam Long cartId) throws Exception {
      List<ProductsInCartEntity> entities=  productsInCartsExternalDbService.getProducts(cartId);
      return ResponseEntity.ok(cartMapper.mapProductsDtoListToCart(productsInCartMapper.mapProductInCartToProductDtoList(entities)));
    }

    @PostMapping(value = "/add_product")
    public ResponseEntity<ProductDto> addProductToCart(@RequestParam Long productId, @RequestParam Long cardId, @RequestParam int quantity) throws Exception {
       ProductsInCartEntity entity= productsInCartsExternalDbService.addProduct(cardId,productId,quantity);
       return ResponseEntity.ok(productsInCartMapper.mapProductInCartToProductDto(entity));
    }

    @DeleteMapping(value = "/remove_product")
    public ResponseEntity<Void> removeProductFromCart(@RequestParam Long productId, @RequestParam Long cardId) throws Exception {
        productsInCartsExternalDbService.deleteProduct(cardId,productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create_order")
    public ResponseEntity<OrderDto> createOrderFromCart(@RequestParam Long cartId, @RequestParam Long userId) throws Exception {
        CartEntity cartEntity=cartDbService.findCartById(cartId);
        //zmienic na service jak sie pojawi/////
        UserEntity userEntity=userRepository.findById(userId).orElseThrow(Exception::new);
        //zamienic na service jak się pojawi////
        OrderEntity orderEntity=new OrderEntity(null,cartEntity, userEntity,cartEntity.getCreationDate(),
        productsInCartsExternalDbService.calculateCartSum(cartId), true,LocalDate.now(),false,null);
        orderRepository.save(orderEntity);
        //zamienic na service jak się pojawi////
        return ResponseEntity.ok(new OrderDto(orderEntity.getId(), orderEntity.getCartId().getId(), orderEntity.getUser().getId(),
                LocalDate.now(), 30.0, true, LocalDate.now(), false, LocalDate.now()));
    }
}
