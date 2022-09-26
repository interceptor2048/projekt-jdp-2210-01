package com.kodilla.ecommercee.arkadiuszj;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name = "groups")
public class ProductGroupEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_group_id")
    private Long id;

    @Column(name = "product_group_name")
    private String groupName;

    @Column(name = "product_group_type")
    private String groupType;


//   @OneToMany(fetch = FetchType.LAZY, mappedBy = "product_group_id")
//   private List<ProductEntity> products;

}
