package com.kodilla.ecommercee.domain.entity;
import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
//actual DB structure
public class CartAndProductsInCartIntegrationTest {
    @Autowired
    CartEntityRepository cartEntityRepository;
    @Autowired
    ProductRepository productRepository;
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
    void updateProductsInCart(){
        //given
        CartEntity cartEntity = new CartEntity(null,null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,666);
        cartEntityRepository.save(cartEntity);
        ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,cartEntity,null,20.00,10,200,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity);
        ProductsInCartEntity productsInCartEntity2=new ProductsInCartEntity(null,cartEntity,null,20.00,20,400,LocalDate.now());
        productsInCartRepository.save(productsInCartEntity2);
        //when
        List<ProductsInCartEntity> products=  productsInCartRepository.findAllByCartId(cartEntity);
        productsInCartEntity2=products.get(1);
        productsInCartEntity2.setProductSumValue(410);
        productsInCartRepository.save(productsInCartEntity2);
        products=  productsInCartRepository.findAllByCartId(cartEntity);
        //then
        assertEquals(products.size(),2);
        assertEquals(products.get(1).getProductSumValue(),410);
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
   @Test
   void  findProductsInCartEntityByCartIdAndAndProductIdTest(){
        //given
       CartEntity cartEntity =new CartEntity(null,null,null, LocalDate.now(),LocalDate.now(),null,0.00);
       cartEntityRepository.save(cartEntity);
       ProductEntity productEntity=new ProductEntity(null,"test name","test description",54.0,null,null);
       productRepository.save(productEntity);
       ProductsInCartEntity productsInCartEntity=new ProductsInCartEntity(null,cartEntity,productEntity,productEntity.getPrice(),10,productEntity.getPrice()*10,LocalDate.now());
       productsInCartRepository.save(productsInCartEntity);
       //when
        Optional<ProductsInCartEntity> entity=  productsInCartRepository.findProductsInCartEntityByCartIdAndAndProductId(cartEntity,productEntity);
        //then
        assertEquals(entity.get().getProductQuantity(),10);
   }

}
