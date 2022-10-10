package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.CartResponseDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartMapper {
    public CartEntity mapToNewCart(final CartDto cartDto){
        return new CartEntity(cartDto.getId(), null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0);
    }

    public CartResponseDto mapProductsDtoListToCart(List<ProductDto> productDtoList){
        CartResponseDto newResponse=new CartResponseDto(new ArrayList<>());
        newResponse.getProductsInCart().addAll(productDtoList);
        return newResponse;
    }

}
