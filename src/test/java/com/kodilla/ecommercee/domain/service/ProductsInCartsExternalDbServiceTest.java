package com.kodilla.ecommercee.domain.service;

import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import com.kodilla.ecommercee.service.ProductsInCartsExternalDbService;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductsInCartsExternalDbServiceTest {
    @Autowired
    ProductsInCartsExternalDbService controller;
    @Autowired
    CartEntityRepository cartEntityRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductsInCartRepository productsInCartRepository;

    @Test
    void addProductTest() throws Exception {
        //given
        CartEntity cartEntity =new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0.00);
        cartEntityRepository.save(cartEntity);
        ProductEntity productEntity=new ProductEntity("test name","test description",5.5);
        productRepository.save(productEntity);
        //when
         ProductsInCartEntity output= controller.addProduct(cartEntity.getId(),productEntity.getId(),10);
        //then
        assertEquals(output.getProductPrice(),5.5);
        assertEquals(output.getProductSumValue(),55);
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void editProductTest() throws Exception {
        //given
        CartEntity cartEntity =new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0.00);
        cartEntityRepository.save(cartEntity);
        ProductEntity productEntity=new ProductEntity("test name","test description",5.5);
        productRepository.save(productEntity);
        controller.addProduct(cartEntity.getId(),productEntity.getId(),10);
        //when
        ProductsInCartEntity output= controller.editProduct(cartEntity.getId(),productEntity.getId(),1);
        //then
        assertEquals(output.getProductQuantity(),1);
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void getAllProductsTest() throws Exception {
        //given
        CartEntity cartEntity =new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0.00);
        cartEntityRepository.save(cartEntity);
        ProductEntity productEntity=new ProductEntity("test name","test description",5.5);
        ProductEntity productEntity1=new ProductEntity("test name1","test description1",1.5);
        ProductEntity productEntity2=new ProductEntity("test name2","test description2",3.5);
        productRepository.save(productEntity);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
        controller.addProduct(cartEntity.getId(),productEntity.getId(),10);
        controller.addProduct(cartEntity.getId(),productEntity1.getId(),10);
        controller.addProduct(cartEntity.getId(),productEntity2.getId(),10);
        //when
        List<ProductsInCartEntity> output=controller.getProducts(cartEntity.getId());
        //then
        assertEquals(output.size(),3);
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void deleteProductTest() throws Exception {
        //given
        CartEntity cartEntity =new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0.00);
        cartEntityRepository.save(cartEntity);
        ProductEntity productEntity=new ProductEntity("test name","test description",5.5);
        productRepository.save(productEntity);
        controller.addProduct(cartEntity.getId(),productEntity.getId(),10);
        //when
        Long cartId=cartEntity.getId();
        Long productId=productEntity.getId();
        controller.deleteProduct(cartId,productId);
        List<ProductsInCartEntity> products=controller.getProducts(cartEntity.getId());
        //then

        assertTrue(products.isEmpty());
        //cleanup
        productsInCartRepository.deleteAll();
        cartEntityRepository.deleteAll();
        productRepository.deleteAll();
    }

}
