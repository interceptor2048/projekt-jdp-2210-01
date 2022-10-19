package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.GroupCreateDto;
import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public ProductGroupDto mapToGroupDto(final ProductGroupEntity productGroupEntity) {
        return new ProductGroupDto(productGroupEntity.getId(), productGroupEntity.getGroupName());
    }

    public ProductGroupEntity mapToGroup(final GroupCreateDto groupCreateDto) {
        return new ProductGroupEntity(groupCreateDto.getId(),
                groupCreateDto.getName(), groupCreateDto.getGroupType(), new ArrayList<>());
    }

    public List<ProductGroupDto> mapToGroupsListDto(final List<ProductGroupEntity> groupsList) {
        return groupsList.stream()
                .map(group -> new ProductGroupDto(group.getId(), group.getGroupName()))
                .collect(Collectors.toList());
    }
}
