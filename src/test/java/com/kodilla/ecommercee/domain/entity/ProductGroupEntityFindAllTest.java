package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductGroupEntityFindAllTest {
    @Autowired
    private ProductGroupEntityRepository productGroupRepository;


    @Test
    void testProductGroupEntityFindAll() {
        //Given
        ProductGroupEntity productGroup1 = new ProductGroupEntity("Name1","Type1");
        ProductGroupEntity productGroup2 = new ProductGroupEntity("Name2","Type2");
        productGroupRepository.save(productGroup1);
        productGroupRepository.save(productGroup2);

        //When
        List<ProductGroupEntity> findAllGroups = productGroupRepository.findAll();
        Long id1 = productGroup1.getId();
        Long id2 = productGroup2.getId();

        //Then
        assertEquals(2,findAllGroups.size());

        //CleanUp
        productGroupRepository.deleteById(id1);
        productGroupRepository.deleteById(id2);
    }
}
