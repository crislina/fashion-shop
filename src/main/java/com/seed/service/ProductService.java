package com.seed.service;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.seed.entity.Product;

@Validated
public interface ProductService {
	public List<Product> getAllProducts();
	
	public Product getProduct(@Min(value = 1L, message = "Invalid Product ID.") Long id);
	
	public boolean save(Product product);
}
