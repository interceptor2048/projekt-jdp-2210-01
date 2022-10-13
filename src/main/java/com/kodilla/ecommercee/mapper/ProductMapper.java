package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.entity.ProductEntity;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import com.kodilla.ecommercee.errorhandlers.GroupNotFoundException;
import com.kodilla.ecommercee.repository.ProductGroupEntityRepository;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Autowired
    private GroupDbService groupDbService;

    public ProductEntity mapToProduct (final ProductDto productDto) {
        try {
        return new ProductEntity(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDbService.findGroupById(productDto.getGroupId()),
                new ArrayList<>()
        );
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductDto mapToProductDto(final ProductEntity product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<ProductEntity> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
