package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductGroupEntityRepository extends CrudRepository<ProductGroupEntity, Long> {

    List<ProductGroupEntity> findByGroupName(String groupName);

    @Override
    List<ProductGroupEntity> findAll();

    void deleteByGroupName(String groupName);
}
