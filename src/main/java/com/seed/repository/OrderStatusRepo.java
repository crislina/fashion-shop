package com.seed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.seed.entity.OrderStatus;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, Long> {

}
