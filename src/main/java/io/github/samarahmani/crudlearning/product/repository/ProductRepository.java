
package io.github.samarahmani.crudlearning.product.repository;

import io.github.samarahmani.crudlearning.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}