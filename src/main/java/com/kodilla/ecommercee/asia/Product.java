package com.kodilla.ecommercee.asia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Product")
public class Product {

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


  // private Group Group;
  // @ManyToOne
  // @JoinColumn(name = "GROUP_ID")
  // public Group getGroupId() {
    //   return productsList;
    //  }


    // @OneToMany(
  // targetEntity = Product.class,
  // mappedBy = "Group",
  // cascade = CascadeType.ALL,
  // fetch = FetchType.LAZY
    // )
  // public List<Product> getProductsList() {
   //   return productsList;
   //}
  // private void setProductsList(List<Product> productsList) {
  // this.productsList = productsList;
}
