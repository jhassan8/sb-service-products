package com.app.products.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.commons.models.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
