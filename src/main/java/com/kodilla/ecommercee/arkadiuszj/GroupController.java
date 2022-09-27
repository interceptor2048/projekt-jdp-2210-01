package com.kodilla.ecommercee.arkadiuszj;

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

@PostMapping
public ResponseEntity<Void> addProductGroup(@RequestParam String groupName) {
    return ResponseEntity.ok(null);
}

@GetMapping(value = "/{groupId}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable Long groupId) {
    return ResponseEntity.ok(new ProductGroupDto(0L, "Ubrania"));
}

@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody ProductGroupDto productGroupDto) {
    return ResponseEntity.ok().build();
}
}
