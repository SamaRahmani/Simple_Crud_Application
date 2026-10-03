package io.github.samarahmani.crudlearning.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
@Entity
@Table(name = "products")
public class Product {


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_description")
    private String productDesc;

    @Column(name = "product_rating")
    private Double productRating;
}
