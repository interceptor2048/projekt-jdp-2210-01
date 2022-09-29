package com.kodilla.ecommercee.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cartId")
    private OrderEntity orderId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartId")
    private List<ProductsInCartEntity> productsInCartId;

    @Column(name = "cart_creation_date")
    private LocalDate creationDate;

    @Column(name = "cart_update_date")
    private LocalDate updateDate;

    @Column(name = "cart_closing_date")
    private LocalDate closingDate;

    @Column(name = "cart_sum_value")
    private double sumValue;


}
