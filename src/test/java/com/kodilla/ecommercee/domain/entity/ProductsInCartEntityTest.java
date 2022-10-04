package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductsInCartEntityTest {

    @Autowired
    ProductsInCartRepository productsInCartRepository;



    @Test
    void addProductToProductsInCartTest() {
        //Given
        ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,null,null,20.00,10,200,LocalDate.now());
        //When
        productsInCartRepository.save(productsInCartEntity);
        //Then
        Long id = productsInCartEntity.getId();
        Optional<ProductsInCartEntity> readProduct = productsInCartRepository.findById(id);
        assertTrue(readProduct.isPresent());
        assertEquals(readProduct.get().getProductSumValue(),200);

        //CleanUp
        productsInCartRepository.deleteById(id);
    }

    @Test
    void deleteProductToProductsInCartTest() {
        //Given
        ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,null,null,20.00,10,200,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity);
        //When
        Long id = productsInCartEntity.getId();
        productsInCartRepository.deleteById(id);
        Optional<ProductsInCartEntity> readProduct = productsInCartRepository.findById(id);
        //Then
        assertFalse(readProduct.isPresent());
    }
}
