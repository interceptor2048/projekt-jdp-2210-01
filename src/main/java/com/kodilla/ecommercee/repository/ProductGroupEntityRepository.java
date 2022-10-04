package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductGroupEntityRepository extends CrudRepository<ProductGroupEntity, Long> {

    List<ProductGroupEntity> findByGroupName(String groupName);

    void deleteByGroupName(String groupName);
}
