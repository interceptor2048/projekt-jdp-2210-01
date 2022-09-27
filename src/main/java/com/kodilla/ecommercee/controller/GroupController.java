package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product_groups")
@CrossOrigin("*")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getProductGroups() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(new ProductGroupDto(0L, "Ubrania"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok().build();
    }
}
