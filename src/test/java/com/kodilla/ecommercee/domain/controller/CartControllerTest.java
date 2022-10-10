package com.kodilla.ecommercee.domain.controller;

import com.kodilla.ecommercee.controller.CartController;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartResponseDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.repository.*;
import com.kodilla.ecommercee.service.CartDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartControllerTest {

    @Autowired
    CartController cartController;
    @Autowired
    CartDbService cartDbService;

    @Autowired
    ProductGroupEntityRepository productGroupEntityRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartEntityRepository cartEntityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsInCartRepository productsInCartRepository;

    @Autowired
    OrderRepository orderRepository;


    @Test
    void createEmptyCartTest() {
        //given
        CartDto cartDto=new CartDto(null,null,null);
        //when
        ResponseEntity<Void>entity= cartController.createEmptyCart(cartDto);
        //then
        assertEquals(200,entity.getStatusCode().value());
        //cleanup
        cartEntityRepository.deleteAll();
    }



    @Test
    void addProductToCartTest() throws Exception {
        //given
        CartEntity cartEntity=new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,0.0);
        cartDbService.saveCart(cartEntity);
        ProductGroupEntity groupEntity=new ProductGroupEntity("test group","test type");
        productGroupEntityRepository.save(groupEntity);
        ProductEntity productEntity=new ProductEntity(null,"test name","test description",54.0,groupEntity,null);
        productRepository.save(productEntity);
        //when
        ResponseEntity<ProductDto> entity= cartController.addProductToCart(productEntity.getId(),cartEntity.getId(),10);
        //then
        assertEquals(200,entity.getStatusCode().value());
        assertEquals(entity.getBody().getName(),"test name");
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
        productGroupEntityRepository.deleteAll();


    }

    @Test
    void getProductsFromCartTest() throws Exception {

        //given
        CartEntity cartEntity=new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,0.0);
        cartDbService.saveCart(cartEntity);
        ProductGroupEntity groupEntity=new ProductGroupEntity("test group","test type");
        productGroupEntityRepository.save(groupEntity);
        ProductEntity productEntity1=new ProductEntity(null,"test name1","test description",54.0,groupEntity,null);
        ProductEntity productEntity2=new ProductEntity(null,"test name2","test description",54.0,groupEntity,null);
        ProductEntity productEntity3=new ProductEntity(null,"test name3","test description",54.0,groupEntity,null);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
        productRepository.save(productEntity3);
        cartController.addProductToCart(productEntity1.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity2.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity3.getId(),cartEntity.getId(),10);
        //when
        ResponseEntity<CartResponseDto> entity=cartController.getProductsFromCart(cartEntity.getId());
        //then
        assertEquals(200,entity.getStatusCode().value());
        assertEquals(3,entity.getBody().getProductsInCart().size());
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
        productGroupEntityRepository.deleteAll();

    }

    @Test
    void removeProductFromCartTest() throws Exception {

        //given
        CartEntity cartEntity=new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,0.0);
        cartDbService.saveCart(cartEntity);
        ProductGroupEntity groupEntity=new ProductGroupEntity("test group","test type");
        productGroupEntityRepository.save(groupEntity);
        ProductEntity productEntity1=new ProductEntity(null,"test name1","test description",54.0,groupEntity,null);
        ProductEntity productEntity2=new ProductEntity(null,"test name2","test description",54.0,groupEntity,null);
        ProductEntity productEntity3=new ProductEntity(null,"test name3","test description",54.0,groupEntity,null);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
        productRepository.save(productEntity3);
        cartController.addProductToCart(productEntity1.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity2.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity3.getId(),cartEntity.getId(),10);
        //when
        ResponseEntity<Void> entity=  cartController.removeProductFromCart(productEntity1.getId(),cartEntity.getId());
        ResponseEntity<CartResponseDto> entities=cartController.getProductsFromCart(cartEntity.getId());
        //then
        assertEquals(200,entity.getStatusCode().value());
        assertEquals(2,entities.getBody().getProductsInCart().size());
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
        productGroupEntityRepository.deleteAll();

    }

    @Test
    void createOrderFromCartTest() throws Exception {

        //given
        UserEntity user = new UserEntity(null, "testFirstName", "testLastName","test","test@test.cm","test","test",LocalDate.now(),1L,1,new ArrayList<>());
        userRepository.save(user);
        CartEntity cartEntity=new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,0.0);
        cartDbService.saveCart(cartEntity);
        ProductGroupEntity groupEntity=new ProductGroupEntity("test group","test type");
        productGroupEntityRepository.save(groupEntity);
        ProductEntity productEntity1=new ProductEntity(null,"test name1","test description",54.0,groupEntity,null);
        ProductEntity productEntity2=new ProductEntity(null,"test name2","test description",54.0,groupEntity,null);
        ProductEntity productEntity3=new ProductEntity(null,"test name3","test description",54.0,groupEntity,null);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
        productRepository.save(productEntity3);
        cartController.addProductToCart(productEntity1.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity2.getId(),cartEntity.getId(),10);
        cartController.addProductToCart(productEntity3.getId(),cartEntity.getId(),10);
        //when
        ResponseEntity<OrderDto> entity=cartController.createOrderFromCart(cartEntity.getId(),user.getId());
        //then
        assertEquals(200,entity.getStatusCode().value());
        assertEquals(cartEntity.getId(),entity.getBody().getCartId());
        assertEquals(user.getId(),entity.getBody().getUserId());
        //cleanup
        orderRepository.deleteAll();
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        productGroupEntityRepository.deleteAll();


    }

}
