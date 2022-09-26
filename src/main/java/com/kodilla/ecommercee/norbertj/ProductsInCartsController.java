package com.kodilla.ecommercee.norbertj;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("/products_in_carts")
@AllArgsConstructor
public class ProductsInCartsController {
    @PostMapping("/add_product")
    public ResponseEntity<ProductsInCartDto> addProduct(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int productQuantity){
        return ResponseEntity.ok(new ProductsInCartDto(cartId,productId,555.55,productQuantity,555.555, LocalDate.now()));
    }
    @PutMapping("/edit_product")
    public ResponseEntity<ProductsInCartDto> editProduct(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int productQuantity){
        return ResponseEntity.ok(new ProductsInCartDto(cartId,productId,555.55,productQuantity,555.555, LocalDate.now()));
    }
    @DeleteMapping ("/delete_product")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long cartId, @RequestParam Long productId){
      return ResponseEntity.ok().build();
    }
}
