package com.app.products.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.products.models.entity.Product;
import com.app.products.models.service.IProductService;

@RestController
public class ProductController {

	private IProductService iProductService;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	public ProductController(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	
	@GetMapping("/products")
	public List<Product> products() {
		return this.iProductService.findAll().stream().map(p -> {
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/product/{id}")
	public Product product(@PathVariable Long id) throws Exception {
		Product product = this.iProductService.findById(id);
		product.setPort(port);
		
		return product;
	}
	
}
