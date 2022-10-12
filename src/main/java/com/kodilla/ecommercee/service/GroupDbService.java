package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.errorhandlers.GroupNotFoundException;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDbService {
    private final ProductGroupEntityRepository productGroupEntityRepository;

    public List<ProductGroupEntity> findAllProductsGroup(){
        return productGroupEntityRepository.findAll();
    }

    public ProductGroupEntity findGroupById(final Long id) throws GroupNotFoundException {
        return productGroupEntityRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public ProductGroupEntity createGroup(final ProductGroupEntity productGroupEntity){
        return  productGroupEntityRepository.save(productGroupEntity);
    }
}
