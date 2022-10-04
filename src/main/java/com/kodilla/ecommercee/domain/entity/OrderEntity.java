package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @OneToOne
    @JoinColumn(name= "order_cart_id", referencedColumnName = "cart_id")
    private CartEntity cartId;

    @ManyToOne
    @JoinColumn(name = "order_user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private UserEntity userId;

    @Column(name="order_creation_date")
    private LocalDate creationDate;

    @Column(name="order_cost")
    private double cost;

    @Column(name="order_is_closed")
    private boolean isClosed;

    @Column(name="order_closing_date")
    private LocalDate closingDate;

    @Column(name="order_is_delivered")
    private boolean isDelivered;

    @Column(name="order_delivery_date")
    private LocalDate deliveryDate;

}
