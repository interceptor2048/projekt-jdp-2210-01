package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductGroupEntityTest {

    @Autowired
    private ProductGroupEntityRepository repository;

    private static final String GROUP_NAME = "Test: name";
    private static final String GROUP_TYPE = "Test: type";

    @Test
    void testProductGroupEntitySave() {
        //Given
        ProductGroupEntity productGroup = new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);

        //Wehn
        repository.save(productGroup);

        //Then
        Long id = productGroup.getId();
        Optional<ProductGroupEntity> group = repository.findById(id);
        assertTrue(group.isPresent());

        //CleanUp
        repository.deleteById(id);
    }

    @Test
    void testProductGroupEntityFindByGroupName() {
        //Given
        ProductGroupEntity productGroup = null;//new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);
        for(int i = 0; i < 3; i++) {
            productGroup = new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);
            repository.save(productGroup);
        }

        String groupName = productGroup.getGroupName();

        //When
        List<ProductGroupEntity> readProductsGroup = repository.findByGroupName(groupName);

        //Then
        assertEquals(3, readProductsGroup.size());

        //CleanUp
        repository.deleteByGroupName(groupName);
    }
}
