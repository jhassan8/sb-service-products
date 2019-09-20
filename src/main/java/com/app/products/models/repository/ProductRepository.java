package com.app.products.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.products.models.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
