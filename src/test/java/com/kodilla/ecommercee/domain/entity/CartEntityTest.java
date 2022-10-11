package com.kodilla.ecommercee.domain.entity;


import com.kodilla.ecommercee.repository.CartEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartEntityTest {

    @Autowired
    CartEntityRepository cartEntityRepository;



    @Test
    void testAddEmptyCart() {
        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,666);
        //When
        cartEntityRepository.save(cartEntity);

        //Then
        Long id = cartEntity.getId();
        Optional<CartEntity> readCart = cartEntityRepository.findById(id);
        assertTrue(readCart.isPresent());
        assertEquals(cartEntity.getSumValue(), readCart.get().getSumValue());

        //CleanUp
        cartEntityRepository.deleteById(id);
    }

    @Test
    void testDeleteEmptyCart() {
        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,666);
        cartEntityRepository.save(cartEntity);
        //When
        cartEntityRepository.deleteById(cartEntity.getId());

        //Then
        Long id = cartEntity.getId();
        Optional<CartEntity> readCart = cartEntityRepository.findById(id);
        assertFalse(readCart.isPresent());
    }

    @Test
    void testUpdateCart() {
        //Given
        CartEntity cartEntity = new CartEntity(null,null,null,LocalDate.now(),LocalDate.now(),null,666);
        cartEntityRepository.save(cartEntity);
        //When
        Long id = cartEntity.getId();
        Optional<CartEntity> readCart = cartEntityRepository.findById(id);
        cartEntity=readCart.get();
        cartEntity.setSumValue(1000);
        cartEntityRepository.save(cartEntity);
        //Then
        id = cartEntity.getId();
        readCart = cartEntityRepository.findById(id);
        assertTrue(readCart.isPresent());
        assertEquals( readCart.get().getSumValue(),1000);
        //CleanUp
        cartEntityRepository.deleteById(id);
    }



    @Test
    //internal array tests
    void testAddProductToEmptyCart() {
        //Given
        CartEntity cartEntity = new CartEntity(null,null,new ArrayList<>(),LocalDate.now(),LocalDate.now(),null,666);
        ProductsInCartEntity productsInCartEntity1=new ProductsInCartEntity(1L,cartEntity,new ProductEntity(),60,20,1200,LocalDate.now());
        ProductsInCartEntity productsInCartEntity2=new ProductsInCartEntity(2L,cartEntity,new ProductEntity(),100,20,2000,LocalDate.now());
        cartEntity.getProductsInCartId().add(productsInCartEntity1);
        cartEntity.getProductsInCartId().add(productsInCartEntity2);

        //When
        cartEntityRepository.save(cartEntity);
        Long cartID=cartEntity.getId();
        Long entityId1=cartEntity.getProductsInCartId().get(0).getId();
        Long entityId2=cartEntity.getProductsInCartId().get(1).getId();

        //Then
        assertEquals(cartEntity.getProductsInCartId().get(entityId1.intValue()-1).getProductSumValue(),1200);
        assertEquals(cartEntity.getProductsInCartId().get(entityId2.intValue()-1).getProductSumValue(),2000);

        //CleanUp
        cartEntityRepository.deleteById(cartID);
    }

}
