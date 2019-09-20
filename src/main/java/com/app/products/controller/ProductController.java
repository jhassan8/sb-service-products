package com.app.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.products.models.entity.Product;
import com.app.products.models.service.IProductService;

@RestController
public class ProductController {

	private IProductService iProductService;
	
	@Autowired
	public ProductController(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	
	@GetMapping("/products")
	public List<Product> products() {
		return this.iProductService.findAll();
	}
	
	@GetMapping("/product/{id}")
	public Product product(@PathVariable Long id) {
		return this.iProductService.findById(id);
	}
	
}
