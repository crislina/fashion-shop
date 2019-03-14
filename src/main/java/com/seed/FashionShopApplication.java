package com.seed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seed.entity.OrderStatus;
import com.seed.entity.Product;
import com.seed.service.OrderService;
import com.seed.service.ProductService;

@SpringBootApplication
public class FashionShopApplication {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(FashionShopApplication.class, args);
	}

	@PostConstruct
    private void initDb() {
		productService.save(new Product("505 cropped trousers No FFC", 169.00, "Alernative length bottoms", null,50));
		productService.save(new Product("AC ALPHA SHORT I", 169.00, "Short bottoms", null,10));
		productService.save(new Product("ALPHA SKINNY SEASONAL II", 169.00, "Short bottoms", null,11));
		productService.save(new Product("LINEN SHIRT SS", 169.00, "Woven shirts", null,18));
		orderService.saveStatus(new OrderStatus(1L, "Pending"));
		orderService.saveStatus(new OrderStatus(2L, "Paid"));
		orderService.saveStatus(new OrderStatus(3L, "Packed"));
		orderService.saveStatus(new OrderStatus(4L, "Delivered"));
	}
}
