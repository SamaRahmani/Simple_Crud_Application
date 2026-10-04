package io.github.samarahmani.crudlearning.product.controller;


import io.github.samarahmani.crudlearning.product.dto.ProductRequestDTO;
import io.github.samarahmani.crudlearning.product.dto.ProductResponseDTO;
import io.github.samarahmani.crudlearning.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Contructor DI
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO) {
        return productService.updateProduct(id, requestDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}")

    public ProductResponseDTO updateProductPartially(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO) {
        return productService.updateProductPartially(id, requestDTO);

    }
}





