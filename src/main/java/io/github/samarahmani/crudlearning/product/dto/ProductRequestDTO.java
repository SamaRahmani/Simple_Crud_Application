package io.github.samarahmani.crudlearning.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    private String productName;

    private Double productPrice;

    private String productDescription;

    private Double productRating;


}
