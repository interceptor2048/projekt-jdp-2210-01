package com.kodilla.ecommercee.domain.entity;
import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//actual DB structure
public class CartAndProductsInCartIntegrationTests {
    @Autowired
    CartEntityRepository cartEntityRepository;
    @Autowired
    ProductsInCartRepository productsInCartRepository;


    @Test
    //actual DB structure
    void AddProductsToNewCartTest() {
        //given
        CartEntity cartEntity = new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,666);
        cartEntityRepository.save(cartEntity);
        ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,cartEntity,null,20.00,10,200,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity);
        ProductsInCartEntity productsInCartEntity2=new ProductsInCartEntity(null,cartEntity,null,20.00,20,400,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity2);
        //when
        List<ProductsInCartEntity> products=  productsInCartRepository.findAllByCartId(cartEntity);

        //then
        assertEquals(products.size(),2);
        assertEquals(products.get(0).getProductQuantity(),10);
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
    }
    @Test
    void deleteProductsToNewCartTest() {
        //given
        CartEntity cartEntity = new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,666);
        cartEntityRepository.save(cartEntity);
        ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,cartEntity,null,20.00,10,200,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity);
        ProductsInCartEntity productsInCartEntity2=new ProductsInCartEntity(null,cartEntity,null,20.00,20,400,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity2);
        //when
        productsInCartRepository.deleteAllByCartId(cartEntity);
        List<ProductsInCartEntity> products=  productsInCartRepository.findAllByCartId(cartEntity);

        //then
        assertEquals(products.size(),0);
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
    }
}
