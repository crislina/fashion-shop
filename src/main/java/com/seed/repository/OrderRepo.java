package com.seed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seed.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o Where o.orderStatusId=:statusId")
	public Order getOrderByStatus(@Param("statusId") Long statusId); 
}
