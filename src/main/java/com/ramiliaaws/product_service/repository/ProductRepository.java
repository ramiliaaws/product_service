package com.ramiliaaws.product_service.repository;

import com.ramiliaaws.product_service.dto.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
