package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "Products")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id", unique = true)
    private Long id;

    @NonNull
    @Column(name = "product_name")
    private String name;

    @NonNull
    @Column(name = "product_description")
    private String description;

    @NonNull
    @Column(name = "product_price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_group_id")
    @JsonBackReference
    private ProductGroupEntity productGroup;

    @OneToMany(mappedBy = "productId")
    @JsonManagedReference
    private List<ProductsInCartEntity> productsInCart;
}
