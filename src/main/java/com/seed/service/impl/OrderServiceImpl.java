package com.seed.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.entity.Log;
import com.seed.entity.Order;
import com.seed.entity.OrderDetail;
import com.seed.entity.OrderStatus;
import com.seed.entity.Product;
import com.seed.repository.LogRepo;
import com.seed.repository.OrderDetailRepo;
import com.seed.repository.OrderRepo;
import com.seed.repository.OrderStatusRepo;
import com.seed.repository.ProductRepo;
import com.seed.service.OrderService;
import com.seed.utils.ConstantUtil;



@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOG= LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	
	@Autowired
	private OrderStatusRepo orderStatusRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private LogRepo logRepo;
	
	@Override
	public Order create(Order order) {
		// TODO Auto-generated method stub
		order.setCreatedDate(new Date());
		Order created=orderRepo.save(order);
		return created;
	}

	@Override
	public void update(Order order,Long productId, int quantity) {
		// TODO Auto-generated method stub
		boolean exist=false;
		if(order.getOrderDetails()!=null) {
		for(OrderDetail detail:order.getOrderDetails()) {
			LOG.info("Add number quantity of existing items in cart");
			if(detail.getProductId()==productId) {
				detail.setQuantity(detail.getQuantity()+quantity);
				detail.setModifiedDate(new Date());
				orderDetailRepo.save(detail);
				exist=true;
			}
		}
		}
		if(!exist) {
			LOG.info("Add new items to cart");
			OrderDetail detail=new OrderDetail();
			detail.setCreatedDate(new Date());
			detail.setModifiedDate(new Date());
			//detail.setOrder(order);
			detail.setOrderId(order.getId());
			detail.setProductId(productId);
			detail.setQuantity(quantity);
			Log log=new Log();
			log.setCreatedDate(new Date());
			log.setAction("Add to cart");
			log.setProductId(productId);
			log.setQuantity(quantity);
			logRepo.save(log);
			orderDetailRepo.save(detail);
		}
		orderDetailRepo.flush();
		double total=orderDetailRepo.getOrderTotalPriceByOrderId(order.getId());
		order.setTotalPrice(total);
		orderRepo.save(order);
		Product product=productRepo.findById(productId).orElse(null);
		product.setQuantity(product.getQuantity()-quantity);
		productRepo.save(product);
		
		/*Order created=retrieveCurrentOrder();
		return created;*/
	}

	@Override
	public Iterable<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll() ;
	}

	@Override
	public Order retrieveCurrentOrder() {
		// TODO Auto-generated method stub
		Order result=orderRepo.getOrderByStatus(ConstantUtil.ORDER_STATUS_PENDING);
		if(result==null) {
			Order newOrder=new Order();
			LOG.info("create new order");
			newOrder.setOrderStatusId(ConstantUtil.ORDER_STATUS_PENDING);
			newOrder.setTotalPrice(0);
			result=create(newOrder);
		}
		return result;
	}

	@Override
	public void saveStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		orderStatusRepo.save(orderStatus);
	}

	@Override
	public Iterable<OrderStatus> getAllOrderStatus() {
		// TODO Auto-generated method stub
		
		return orderStatusRepo.findAll();
	}

	@Override
	public Order getLatestOrder() {
		// TODO Auto-generated method stub
		Order result=orderRepo.getOrderByStatus(ConstantUtil.ORDER_STATUS_PENDING);
		List<OrderDetail> orderDetails=orderDetailRepo.findAllByOrderId(result.getId());
		result.setOrderDetails(null);
		result.setOrderDetails(orderDetails);
		return result;
	}

	@Override
	public void deleteItem(Order order, Long productId) {
		// TODO Auto-generated method stub
		OrderDetail oDetail=orderDetailRepo.getOrderDetailByOrderIdAndProductId(order.getId(), productId);
		orderDetailRepo.delete(oDetail);
		double total=orderDetailRepo.getOrderTotalPriceByOrderId(order.getId());
		order.setTotalPrice(total);
		orderRepo.save(order);
		Product product=productRepo.findById(productId).orElse(null);
		product.setQuantity(product.getQuantity()+oDetail.getQuantity());
		productRepo.save(product);
		Log log=new Log();
		log.setCreatedDate(new Date());
		log.setAction("Remove from cart");
		log.setProductId(productId);
		log.setQuantity(oDetail.getQuantity());
		logRepo.save(log);
	}

	@Override
	public Order checkOut() {
		// TODO Auto-generated method stub
		LOG.info("check out");
		Order order=retrieveCurrentOrder();
		order.setOrderStatusId(ConstantUtil.ORDER_STATUS_PAID);
		Order result = orderRepo.save(order);
		Log log=new Log();
		log.setCreatedDate(new Date());
		log.setAction("Check out order number:"+order.getId());
		log.setProductId(null);
		log.setQuantity(0);
		logRepo.save(log);
		return result;
	}

}
