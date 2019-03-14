package com.seed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seed.entity.Product;
import com.seed.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public Iterable<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Product getProductById(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}
}
