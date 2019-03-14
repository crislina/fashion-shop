package com.seed.service;

import com.seed.entity.Order;
import com.seed.entity.OrderStatus;

public interface OrderService {
	
	public Order create(Order order);
	
	public void update(Order order, Long productId, int quantity);
	
	public Iterable<Order> getAllOrders();
	
	public Order retrieveCurrentOrder();
	
	public Order getLatestOrder();
	
	public void saveStatus(OrderStatus orderStatus);
	
	public Iterable<OrderStatus> getAllOrderStatus();
	
	public void deleteItem(Order order, Long productId);
	
	public Order checkOut();
}
