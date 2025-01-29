package com.tap.model;

import java.util.Date;

public class Order {
	
	private int orderId;
	private int userId;
	private int restaurantId;
	private Date orderDate;
	private float totalAmount;
	private String status;
	private String address;
	private String phone;
	private String paymentMode;
	
	public Order() {
		
	}

	public Order(int orderId, int userId, int restaurantId, Date orderDate, float totalAmount, String status,
			String address,String phone,String paymentMode) {
		
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.address = address;
		this.phone = phone;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	@Override
	public String toString() {
		
		return orderId+" "+userId+" "+restaurantId+" "+orderDate+" "+totalAmount+" "+status+" "+address+" "+phone+" "+paymentMode;
	}
	
}
