package com.seed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.seed.entity.Order;
import com.seed.entity.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

	List<OrderDetail> findAllByOrderId(Long id);

	@Query("SELECT o FROM OrderDetail o Where o.orderId=:orderId and o.productId=:productId")
	public OrderDetail getOrderDetailByOrderIdAndProductId(@Param("orderId") Long orderId, @Param("productId") Long productId); 
	
	@Query(value="SELECT sum(o.quantity*p.price) FROM order_details o left join product p on o.product_id=p.id Where o.order_id=:orderId",nativeQuery=true)
	public double getOrderTotalPriceByOrderId(@Param("orderId") Long orderId);
}
