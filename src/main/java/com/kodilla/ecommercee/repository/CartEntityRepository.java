package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface CartEntityRepository extends CrudRepository<CartEntity,Long> {
    @Override
    Optional<CartEntity> findById(Long id);

}
