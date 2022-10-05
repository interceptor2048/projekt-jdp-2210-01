package com.kodilla.ecommercee.domain.entity;

import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductGroupEntityTest {

    @Autowired
    private ProductGroupEntityRepository productGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String GROUP_NAME = "Test: name";
    private static final String GROUP_TYPE = "Test: type";

    @Test
    void testProductGroupEntitySave() {
        //Given
        ProductGroupEntity productGroup = new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);

        //Wehn
        productGroupRepository.save(productGroup);

        //Then
        Long id = productGroup.getId();
        Optional<ProductGroupEntity> group = productGroupRepository.findById(id);
        assertTrue(group.isPresent());

        //CleanUp
        productGroupRepository.deleteById(id);
    }

    @Test
    void testProductGroupEntityFindByGroupName() {
        //Given
        ProductGroupEntity productGroup = null;//new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);
        for(int i = 0; i < 3; i++) {
            productGroup = new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);
            productGroupRepository.save(productGroup);
        }

        String groupName = productGroup.getGroupName();

        //When
        List<ProductGroupEntity> readProductsGroup = productGroupRepository.findByGroupName(groupName);

        //Then
        assertEquals(3, readProductsGroup.size());

        //CleanUp
        productGroupRepository.deleteByGroupName(groupName);
    }

    @Test
    void testProductGroupEntityDelete() {
        //Given
        ProductGroupEntity productGroup = new ProductGroupEntity("name", "type");
        productGroupRepository.save(productGroup);

        //When
        Long id = productGroup.getId();
        productGroupRepository.deleteById(id);

        //Then
        Optional<ProductGroupEntity> group = productGroupRepository.findById(id);
        assertFalse(group.isPresent());
    }

    @Test
    void testProductGroupEntitySaveWithProductEntity() {
        //Given
        ProductGroupEntity productGroup = new ProductGroupEntity(GROUP_NAME, GROUP_TYPE);
        ProductEntity product = new ProductEntity(
                "name product", "description product", 20.45
        );
        ProductEntity product2 = new ProductEntity(
                "name product2", "description product2", 40.45
        );
        productGroup.getProducts().add(product);
        productGroup.getProducts().add(product2);
        product.setProductGroupId(productGroup);
        product2.setProductGroupId(productGroup);

        //When
        productGroupRepository.save(productGroup);
        productRepository.save(product);
        productRepository.save(product2);

        //Then
        Long productGroupId = productGroup.getId();

        List<ProductEntity> productEntities =
                productRepository.findByProductGroupId_Id(productGroupId);
        Optional<ProductGroupEntity> readProductGroup =
                productGroupRepository.findById(productGroupId);

        assertEquals(2, productEntities.size());
        assertTrue(readProductGroup.isPresent());
    }
}
