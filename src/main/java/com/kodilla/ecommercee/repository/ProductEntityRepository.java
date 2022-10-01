package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductEntityRepository extends CrudRepository<ProductEntity, Long> {

}
