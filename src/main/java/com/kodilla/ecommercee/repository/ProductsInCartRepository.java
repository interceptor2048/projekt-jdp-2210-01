package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.CartEntity;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductsInCartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface ProductsInCartRepository extends CrudRepository<ProductsInCartEntity,Long> {
    @Override
    Optional<ProductsInCartEntity> findById(Long id);

    List<ProductsInCartEntity>findAllByCartId(CartEntity cartId);

    void deleteAllByCartId(CartEntity cartId);


    Optional<ProductsInCartEntity> findProductsInCartEntityByCartIdAndAndProductId(CartEntity cartEntity,ProductEntity productEntity);

}

