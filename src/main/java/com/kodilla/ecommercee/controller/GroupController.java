package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GroupNotFoundException;
import com.kodilla.ecommercee.domain.dto.GroupCreateDto;
import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import com.kodilla.ecommercee.domain.entity.ProductGroupEntity;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_groups")
@CrossOrigin("*")
@RequiredArgsConstructor
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupDbService groupDbService;

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getProductGroups() {
        List<ProductGroupEntity> groupsList = groupDbService.findAllProductsGroup();
        return ResponseEntity.ok(groupMapper.mapToGroupsListDto(groupsList));
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        try {
            return ResponseEntity.ok(groupMapper.mapToGroupDto(groupDbService.findGroupById(groupId)));
        } catch (GroupNotFoundException g){
            return new ResponseEntity<>(
                    new ProductGroupDto(groupId,"Group With This ID Not exist"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProductGroup(@RequestBody GroupCreateDto groupCreateDto) {
        ProductGroupEntity productGroupEntity = groupMapper.mapToGroup(groupCreateDto);
        groupDbService.createGroup(productGroupEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody  GroupCreateDto groupCreateDto) {
        ProductGroupEntity productGroupEntity = groupMapper.mapToGroup(groupCreateDto);
        ProductGroupEntity updateGroup = groupDbService.createGroup(productGroupEntity);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(updateGroup));
    }
}
