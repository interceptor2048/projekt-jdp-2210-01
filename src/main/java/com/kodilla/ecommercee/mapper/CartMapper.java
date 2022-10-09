package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.entity.CartEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CartMapper {
    public CartEntity mapToNewCart(final CartDto cartDto){
        return new CartEntity(cartDto.getId(), null,new ArrayList<>(), LocalDate.now(),LocalDate.now(),null,0);
    }

}
