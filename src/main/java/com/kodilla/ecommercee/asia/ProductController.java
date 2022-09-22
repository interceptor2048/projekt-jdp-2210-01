package com.kodilla.ecommercee.asia;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(new ProductDto(1L, "book", "thriller", 30.2, 2L));
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(new ProductDto(3L, "pencil", "black", 3.5, 4L));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok().build();
    }
}
