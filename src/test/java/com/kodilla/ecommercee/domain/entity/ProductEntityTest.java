package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductEntityTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductGroupEntity productGroupEntity;
    private ProductsInCartEntity productsInCartEntity;

    @Test
    void testProductEntitySave() {
        //Given
        ProductEntity productEntity = new ProductEntity(1L, "kurtka", "czarna, pikowana, puchowa",
                699.0, null, null);

        //When
        productRepository.save(productEntity);

        //Then
        long id = productEntity.getId();
        Optional<ProductEntity> product = productRepository.findById(id);
        assertTrue(product.isPresent());

        //CleanUp
        productRepository.deleteById(id);

    }

    @Test
    void testProductEntityDelete() {
        //Given
        ProductEntity productEntity = new ProductEntity(2L, "zaprawa", "murarska, szybkowiążąca",
                29.90, null, null);
        productRepository.save(productEntity);

        //When
        productRepository.deleteById(productEntity.getId());

        //Then
        long id = productEntity.getId();
        Optional<ProductEntity> product = productRepository.findById(id);
        assertFalse(product.isPresent());
    }


}
