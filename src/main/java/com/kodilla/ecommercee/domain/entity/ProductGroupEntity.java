package com.kodilla.ecommercee.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "product_groups")
public class ProductGroupEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_group_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productGroupId")
    private List<ProductEntity> products;

    @Column(name = "product_group_name")
    private String groupName;

    @Column(name = "product_group_type")
    private String groupType;


}
