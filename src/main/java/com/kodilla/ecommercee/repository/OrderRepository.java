package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

  @Override
  List<OrderEntity> findAll();
  @Override
  OrderEntity save (OrderEntity orderEntity);

  @Override
  Optional<OrderEntity> findById(Long id);

}
