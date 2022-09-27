package com.kodilla.ecommercee.arkadiuszj;

import com.kodilla.ecommercee.asia.Product;
import com.kodilla.ecommercee.norbertj.OrderEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class CartEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "order_cart_id")
    private OrderEntity orderEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products_in_cart_id")
    private List<Product> productsInCartId;

    @Column(name = "cart_creation_date")
    private LocalDate creationDate;

    @Column(name = "cart_update_date")
    private LocalDate updateDate;

    @Column(name = "cart_closing_date")
    private LocalDate closingDate;

    @Column(name = "cart_sum_value")
    private double sumValue;


}
