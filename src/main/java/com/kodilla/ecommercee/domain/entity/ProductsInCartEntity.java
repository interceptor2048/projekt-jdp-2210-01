package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "products_in_carts")
public class ProductsInCartEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_in_cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "products_in_cart_cart_id", referencedColumnName = "cart_id")
    private CartEntity cartId;

    @ManyToOne
    @JoinColumn(name = "product_in_cart_product_id", referencedColumnName = "product_id")
    private ProductEntity productId;

    @Column(name="product_in_cart_product_price")
    private double productPrice;

    @Column(name="product_in_cart_product_quantity")
    private int productQuantity;

    @Column(name="product_in_cart_sum_value")
    private double productSumValue;

    @Column(name="product_in_cart_date_added")
    private LocalDate productDateAdded;

}
