package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import com.kodilla.ecommercee.errorhandlers.CartOrProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsInCartInternalDbService {

    private final ProductsInCartRepository productsInCartRepository;

    public ProductsInCartEntity saveProduct(final ProductsInCartEntity entity){
        return productsInCartRepository.save(entity);
    }

    public List<ProductsInCartEntity> getAllProducts(CartEntity entity){
        return productsInCartRepository.findAllByCartId(entity);
    }

    public ProductsInCartEntity getProductInCartByProductAndCart(final ProductEntity productEntity, CartEntity cartEntity) throws CartOrProductNotFoundException {
        return productsInCartRepository.findProductsInCartEntityByCartIdAndAndProductId(cartEntity,productEntity).orElseThrow(CartOrProductNotFoundException::new);
    }

    public void removeProduct(final Long id){
        productsInCartRepository.deleteById(id);
    }



}
