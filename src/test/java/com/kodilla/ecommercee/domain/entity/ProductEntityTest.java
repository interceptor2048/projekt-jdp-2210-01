package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
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
        ProductEntity productEntity = new ProductEntity(null, "kurtka", "czarna, pikowana, puchowa",
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
        ProductEntity productEntity = new ProductEntity(null, "zaprawa", "murarska, szybkowiążąca",
                29.90, null, null);
        productRepository.save(productEntity);

        //When
        productRepository.deleteById(productEntity.getId());

        //Then
        long id = productEntity.getId();
        Optional<ProductEntity> product = productRepository.findById(id);
        assertFalse(product.isPresent());
    }

    @Test
    void testProductEntityFindAll() {
        //Given
        ProductEntity productEntity1 = new ProductEntity(null, "zaprawa", "murarska, szybkowiążąca",
                29.90, null, null);
        ProductEntity productEntity2 = new ProductEntity(null, "kurtka", "czarna, pikowana, puchowa",
                699.0, null, null);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);

        //When
        List<ProductEntity> productList = productRepository.findAll();

        //Then
        assertEquals(2, productList.size());

        //Cleanup
        productRepository.deleteById(productEntity1.getId());
        productRepository.deleteById(productEntity2.getId());

    }

    @Test
    void testProductEntityFindById() {
        //Given
        ProductEntity productEntity1 = new ProductEntity(null, "zaprawa", "murarska, szybkowiążąca",
                29.90, null, null);
        ProductEntity productEntity2 = new ProductEntity(null, "kurtka", "czarna, pikowana, puchowa",
                699.0, null, null);
        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
        long id1 = productEntity1.getId();
        long id2 = productEntity2.getId();

        //When
        List<ProductEntity> orderList = productRepository.findAll();
        Optional<ProductEntity> findByIdProduct = productRepository.findById(id1);

        //Then
        assertTrue(findByIdProduct.isPresent());
        assertEquals(29.90, findByIdProduct.get().getPrice());

        //Cleanup
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);

    }

    @Test
    void testProductEntityUpdate() {
        //Given
        ProductEntity productEntity = new ProductEntity(null, "kurtka", "czarna, pikowana, puchowa",
                699.0, null, null);
        productRepository.save(productEntity);

        //When
        long id = productEntity.getId();
        productEntity.setPrice(725.00);

        //Then
        productRepository.save(productEntity);
        Optional<ProductEntity> product = productRepository.findById(id);
        assertTrue(product.isPresent());
        assertEquals(725, product.get().getPrice());

        //Cleanup
        productRepository.deleteById(id);
    }

}
