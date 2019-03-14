package com.seed.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.entity.Product;
import com.seed.repository.ProductRepo;
import com.seed.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOG= LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		LOG.info("display all product");
		return productRepo.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public boolean save(Product product) {
		// TODO Auto-generated method stub
		boolean result=false;
		Product saved=productRepo.save(product);
		if(saved!=null) {
			result=true; 
		}
		return result;
	}

}
