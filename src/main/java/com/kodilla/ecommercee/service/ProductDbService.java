package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.errorhandlers.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;

    public ProductEntity getProduct(final Long productId) throws ProductNotFoundException{
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public ProductEntity saveProduct (final ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void deleteProduct (final Long productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}
