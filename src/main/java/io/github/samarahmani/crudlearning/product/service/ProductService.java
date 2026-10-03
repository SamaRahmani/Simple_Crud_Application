package io.github.samarahmani.crudlearning.product.service;


import io.github.samarahmani.crudlearning.product.dto.ProductRequestDTO;
import io.github.samarahmani.crudlearning.product.dto.ProductResponseDTO;
import io.github.samarahmani.crudlearning.product.model.Product;
import io.github.samarahmani.crudlearning.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // Create Product
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {

        // DTO → Entity
        Product product = new Product();

        product.setProductName(requestDTO.getProductName());
        product.setProductPrice(requestDTO.getProductPrice());
        product.setProductDesc(requestDTO.getProductDescription());
        product.setProductRating(requestDTO.getProductRating());


        // Save Entity
        Product savedProduct = productRepository.save(product);

        // Entity → Response DTO
        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(savedProduct.getId());
        responseDTO.setProductName(savedProduct.getProductName());
        responseDTO.setProductPrice(savedProduct.getProductPrice());
        responseDTO.setProductDescription(savedProduct.getProductDesc());
        responseDTO.setProductRating(savedProduct.getProductRating());
        // return Response DTO
        return responseDTO;

    }
}
