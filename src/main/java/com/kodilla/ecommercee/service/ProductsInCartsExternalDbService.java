package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.dto.ProductsInCartDto;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import com.kodilla.ecommercee.errorhandlers.CartNotFoundException;
import com.kodilla.ecommercee.mapper.ProductsInCartMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductsInCartInternalDbService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsInCartsExternalDbService {

    ProductsInCartInternalDbService productsInCartDbService;
    ProductRepository productRepository;
    CartDbService cartDbService;


    public ProductsInCartEntity addProduct( Long cartId,  Long productId,  int productQuantity) throws Exception {
       /////////////////////////////////////////////////do poprawy-zastąpić ProductDbService w finalnej wersji//////////
        ProductEntity productEntity=productRepository.findById(productId).orElseThrow(Exception::new);
        //////////////////////////////////////////////////////////////////////////////////////////////
        CartEntity cartEntity=cartDbService.findCartById(cartId);
        ProductsInCartEntity productInCartEntity=new ProductsInCartEntity(null,cartEntity,productEntity,productEntity.getPrice()
                                                 ,productQuantity,productEntity.getPrice()*productQuantity,LocalDate.now());
        productsInCartDbService.saveProduct(productInCartEntity);
        return productInCartEntity;
    }

    public ProductsInCartEntity editProduct( Long cartId, Long productId, int productQuantity) throws Exception {
        /////////////////////////////////////////////////do poprawy-zastąpić ProductDbService w finalnej wersji//////////
        ProductEntity productEntity=productRepository.findById(productId).orElseThrow(Exception::new);
        //////////////////////////////////////////////////////////////////////////////////////////////
        CartEntity cartEntity=cartDbService.findCartById(cartId);
        ProductsInCartEntity productInCartEntity=productsInCartDbService.getProductInCartByProductAndCart(productEntity,cartEntity);
        productInCartEntity.setProductQuantity(productQuantity);
        productInCartEntity.setProductSumValue(productInCartEntity.getProductPrice()*productQuantity);
        productsInCartDbService.saveProduct(productInCartEntity);
         return  productInCartEntity;
    }

    public void deleteProduct(Long cartId, Long productId) throws Exception {
        ProductEntity productEntity=productRepository.findById(productId).orElseThrow(Exception::new);
        /////////////////////////////////////////////////do poprawy-zastąpić ProductDbService w finalnej wersji//////////
        CartEntity cartEntity=cartDbService.findCartById(cartId);
        //////////////////////////////////////////////////////////////////////////////////////////////
        ProductsInCartEntity productInCartEntity=productsInCartDbService.getProductInCartByProductAndCart(productEntity,cartEntity);
        productsInCartDbService.removeProduct(productInCartEntity.getId());
    }


    public List<ProductsInCartEntity> getProducts(Long cartId) throws Exception {
        CartEntity cartEntity=cartDbService.findCartById(cartId);
        return productsInCartDbService.getAllProducts(cartEntity);
    }

    public double calculateCartSum(Long cartId) throws Exception {
        List<ProductsInCartEntity> entities=getProducts(cartId);
        return entities.stream().map(ProductsInCartEntity::getProductSumValue).reduce(0.0,Double::sum);
    }
}
