package com.seed.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seed.entity.Order;
import com.seed.entity.OrderStatus;
import com.seed.request.AddToCartRequest;
import com.seed.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/status", method=RequestMethod.GET)
	public Iterable<OrderStatus> getOrderStatus(){
		return orderService.getAllOrderStatus();
	}
	
	@RequestMapping(value="/current", method=RequestMethod.GET)
	public Order getCurrentOrder() {
		return orderService.retrieveCurrentOrder();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Iterable<Order> getAllOrder() {
		return orderService.getAllOrders();
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Order checkOut() {
		return orderService.checkOut();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Order addToCart(@RequestBody @Valid AddToCartRequest request) {
		Order order=orderService.retrieveCurrentOrder();
		orderService.update(order, request.getProductId(), request.getQuantity());
		Order newOrder=orderService.getLatestOrder();
		return newOrder;
	}
	
	@RequestMapping(value="/item/{id}", method=RequestMethod.DELETE)
	public Order removeItem(@PathVariable("id") Long id) {
		Order order=orderService.retrieveCurrentOrder();
		orderService.deleteItem(order, id);
		Order newOrder=orderService.getLatestOrder();
		return newOrder;
	}
}
