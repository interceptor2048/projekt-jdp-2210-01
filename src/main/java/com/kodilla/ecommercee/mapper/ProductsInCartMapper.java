package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsInCartMapper {
    public ProductDto mapProductInCartToProductDto(final ProductsInCartEntity entity){
        return new ProductDto(entity.getProductId().getId(),entity.getProductId().getName(),entity.getProductId().getDescription(),entity.getProductPrice(),entity.getProductId().getProductGroup().getId());
    }

    public List<ProductDto> mapProductInCartToProductDtoList(final List<ProductsInCartEntity> entities){
        return entities.stream()
                .map(this::mapProductInCartToProductDto)
                .collect(Collectors.toList());
    }






}
