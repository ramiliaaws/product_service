package com.ramiliaaws.product_service;

import com.ramiliaaws.product_service.dto.Product;
import com.ramiliaaws.product_service.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            productService.save(new Product(1L, "Twix", 2.00));
            productService.save(new Product(2L, "Kitkat", 2.50));
            productService.save(new Product(3L, "Rafaello", 6.00));
            productService.save(new Product(4L, "Icecream", 5.00));
            productService.save(new Product(5L, "Ritter sport", 3.00));
        };
    }
}
