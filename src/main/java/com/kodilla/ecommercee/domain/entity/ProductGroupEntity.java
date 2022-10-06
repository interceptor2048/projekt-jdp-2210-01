package com.kodilla.ecommercee.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "product_groups")
public class ProductGroupEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_group_id")
    private Long id;

    @NonNull
    @Column(name = "product_group_name")
    private String groupName;

    @NonNull
    @Column(name = "product_group_type")
    private String groupType;

    @OneToMany(targetEntity = ProductEntity.class,
                mappedBy = "productGroup",
                fetch = FetchType.LAZY
    )
    private List<ProductEntity> products = new ArrayList<>();
}
