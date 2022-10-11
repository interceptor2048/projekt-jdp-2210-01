package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.errorhandlers.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartEntityRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDbService {
    @NotNull
    private final CartEntityRepository cartEntityRepository;

    public CartEntity saveCart(final CartEntity cartEntity){
       return cartEntityRepository.save(cartEntity);
    }

    public CartEntity findCartById(final Long id) throws CartNotFoundException {
        return cartEntityRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }


}
