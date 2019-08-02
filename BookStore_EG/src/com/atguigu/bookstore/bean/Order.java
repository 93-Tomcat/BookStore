package com.atguigu.bookstore.bean;

import java.util.Date;

public class Order {
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 订单商品数量
	 */
	private int totalCount;
	/**
	 * 订单商品金额
	 */
	private double totalAmount;
	/**
	 * 订单创建时间
	 */
	private Date createTime;
	/**
	 * 订单状态：
	 * 	0 未发货
	 * 	1 已发货 
	 * 	2 交易完成
	 */
	private int state;
	/**
	 * 订单所属用户的id
	 */
	private Integer userId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Order(String orderId, int totalCount, double totalAmount, Date createTime, int state, Integer userId) {
		super();
		this.orderId = orderId;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.createTime = createTime;
		this.state = state;
		this.userId = userId;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount
				+ ", createTime=" + createTime + ", state=" + state + ", userId=" + userId + "]";
	}
	
	
	
}
