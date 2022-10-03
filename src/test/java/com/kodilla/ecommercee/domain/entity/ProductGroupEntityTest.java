package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ProductGroupEntityTest {

    @Autowired
    private ProductGroupEntityRepository repository;

    @Test
    void testProductGroupEntitySave() {
        //Given
        ProductGroupEntity productGroup = new ProductGroupEntity("name", "type");

        //Wehn
        repository.save(productGroup);

        //Then
        Long id = productGroup.getId();
        Optional<ProductGroupEntity> group = repository.findById(id);
        assertTrue(group.isPresent());

        //CleanUp
        repository.deleteById(id);
    }


}
