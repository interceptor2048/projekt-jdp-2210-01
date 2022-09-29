package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id", unique = true)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_group_id", referencedColumnName = "product_group_id")
    @JsonBackReference
    private ProductGroupEntity productGroupId;

    @OneToMany(mappedBy = "productId")
    @JsonManagedReference
    private List<ProductsInCartEntity> productsInCart;

}
