package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProductsInCartDto {
    private Long cartId;
    private Long productId;
    private double price;
    private int quantity;
    private double sumValue;
    private LocalDate dateAdded;
}
