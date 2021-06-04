package com.ramiliaaws.product_service.service;

import com.ramiliaaws.product_service.dto.Product;

public interface ProductService {
    Iterable<Product> getAllProducts();

    Product getProduct(long id);

    Product save(Product product);
}
