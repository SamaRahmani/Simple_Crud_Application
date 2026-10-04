package io.github.samarahmani.crudlearning.product.service;


import io.github.samarahmani.crudlearning.product.dto.ProductRequestDTO;
import io.github.samarahmani.crudlearning.product.dto.ProductResponseDTO;
import io.github.samarahmani.crudlearning.product.exception.ProductNotFoundException;
import io.github.samarahmani.crudlearning.product.model.Product;
import io.github.samarahmani.crudlearning.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    // GET all entries using findAll();
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> responseList = new ArrayList<>();

        // Entity → Response DTO
//        for (Product product : products) {
//
//            ProductResponseDTO responseDTO = new ProductResponseDTO();
//
//            responseDTO.setId(product.getId());
//            responseDTO.setProductName(product.getProductName());
//            responseDTO.setProductPrice(product.getProductPrice());
//            responseDTO.setProductDescription(product.getProductDesc());
//            responseDTO.setProductRating(product.getProductRating());
//
//            responseList.add(responseDTO);
//
//        }
//
//        return responseList;


        // This return list using the streams
        return products.stream()
                .map(product -> {
                    ProductResponseDTO responseDTO = new ProductResponseDTO();

                    responseDTO.setId(product.getId());
                    responseDTO.setProductName(product.getProductName());
                    responseDTO.setProductPrice(product.getProductPrice());
                    responseDTO.setProductDescription(product.getProductDesc());
                    responseDTO.setProductRating(product.getProductRating());
                    return responseDTO;
                })
                .toList();

    }

    // GET single entry using findById() and Optional properties:
    public ProductResponseDTO getProductById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        // Product exists
        if (product.isPresent()) {

            Product foundProduct = product.get();

            // Entity → Response DTO
            ProductResponseDTO responseDTO = new ProductResponseDTO();

            responseDTO.setId(foundProduct.getId());
            responseDTO.setProductName(foundProduct.getProductName());
            responseDTO.setProductPrice(foundProduct.getProductPrice());
            responseDTO.setProductDescription(foundProduct.getProductDesc());
            responseDTO.setProductRating(foundProduct.getProductRating());
            return responseDTO;

        }
        // Product doesn't exist
        else {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

    }

    // PUT operation - updating Values
    public ProductResponseDTO updateProduct(
            Long id,
            ProductRequestDTO requestDTO) {

        // Find existing product
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();

            // Update Entity
            existingProduct.setProductName(requestDTO.getProductName());
            existingProduct.setProductPrice(requestDTO.getProductPrice());
            existingProduct.setProductDesc(requestDTO.getProductDescription());
            existingProduct.setProductRating(requestDTO.getProductRating());

            // Save updated Entity
            Product updatedProduct = productRepository.save(existingProduct);

            // Entity → Response DTO
            ProductResponseDTO responseDTO = new ProductResponseDTO();

            responseDTO.setId(updatedProduct.getId());
            responseDTO.setProductName(updatedProduct.getProductName());
            responseDTO.setProductPrice(updatedProduct.getProductPrice());
            responseDTO.setProductDescription(updatedProduct.getProductDesc());
            responseDTO.setProductRating(updatedProduct.getProductRating());

            return responseDTO;

        } else {
            throw new ProductNotFoundException(
                    "Product not found with id: " + id
            );
        }
    }

    // Delete By ID
    public void deleteProduct(Long id) {

        // Check whether product exists
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {

            // Product exists → delete it
            productRepository.delete(product.get());

        } else {

            // Product doesn't exist → 404
            throw new ProductNotFoundException(
                    "Product not found with id: " + id
            );
        }
    }


    // Patch
    public ProductResponseDTO updateProductPartially( Long id,
                                                      ProductRequestDTO requestDTO){
        // 1. Find the existing product
        Optional<Product> productOptional = productRepository.findById(id);
        // 3. Get the existing entity
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + id
            );
        }
        // 3. Get the existing entity
        Product product = productOptional.get();
        // 4. Update only fields provided by the client
        if (requestDTO.getProductName() != null) {
            product.setProductName(requestDTO.getProductName());
        }
        if (requestDTO.getProductPrice() != null) {
            product.setProductPrice(requestDTO.getProductPrice());
        }
        if (requestDTO.getProductDescription() != null) {
            product.setProductDesc(requestDTO.getProductDescription());
        }
        if (requestDTO.getProductRating() != null) {
            product.setProductRating(requestDTO.getProductRating());
        }
        // Save updated entity
        Product updatedProduct = productRepository.save(product);
        //Entity → Response DTO
        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(updatedProduct.getId());
        responseDTO.setProductName(updatedProduct.getProductName());
        responseDTO.setProductPrice(updatedProduct.getProductPrice());
        responseDTO.setProductDescription(updatedProduct.getProductDesc());
        responseDTO.setProductRating(updatedProduct.getProductRating());

        // 7. Return response
        return responseDTO;

    }
}
