package com.seed.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date createdDate;
	
	private double totalPrice;
	
	@Column(name="order_status_id")
	private Long orderStatusId;

	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="order_status_id", referencedColumnName="id",insertable=false,updatable=false)
	private OrderStatus orderStatus;
	
	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.ALL}, orphanRemoval=true, mappedBy="order",fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value= {"order"})
	private List<OrderDetail> orderDetails;
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/*@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="order_status_id",referencedColumnName="id", insertable = false, updatable = false)*/
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order() {};
	
	public Order(Double totalPrice) {
		this.setCreatedDate(new Date());
		this.setTotalPrice(totalPrice);
	}
}
