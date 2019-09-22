package com.app.products.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.commons.models.entity.Product;
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
	
	@GetMapping("/all")
	public List<Product> products() {
		return this.iProductService.findAll().stream().map(p -> {
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/view/{id}")
	public Product product(@PathVariable Long id) throws Exception {
		Product product = this.iProductService.findById(id);
		product.setPort(port);
		
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return product;
	}
	
	@PostMapping("new")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return this.iProductService.save(product);
	}
	
	@PutMapping("update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		Product p = iProductService.findById(id);
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		return this.iProductService.save(p);
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delte(@PathVariable Long id) {
		this.iProductService.deleteById(id);
	}
}
