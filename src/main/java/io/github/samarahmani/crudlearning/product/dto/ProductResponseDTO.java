package io.github.samarahmani.crudlearning.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {



        private Long id;

        private String productName;

        private Double productPrice;

        private String productDescription;

        private Double productRating;


}



