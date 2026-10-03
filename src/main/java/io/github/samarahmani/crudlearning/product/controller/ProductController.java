package io.github.samarahmani.crudlearning.product.controller;


import io.github.samarahmani.crudlearning.product.dto.ProductRequestDTO;
import io.github.samarahmani.crudlearning.product.dto.ProductResponseDTO;
import io.github.samarahmani.crudlearning.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
