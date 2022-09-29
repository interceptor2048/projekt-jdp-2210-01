package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.dto.ProductDto;

import java.util.List;

public class CartDto {
    private Long id;
    private Long userId;
    private List<ProductDto> productsInCart;
}
