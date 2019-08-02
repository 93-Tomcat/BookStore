package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {
	
	
	@Override
	public int updateOrderStateByOrderId(String orderId, int state) {
		String sql = "UPDATE bs_order SET state = ? WHERE order_id = ?";
		return update(sql, state, orderId);
	}
	
	
	@Override
	public List<Order> getOrdersByUserId(int userId) {
		String sql = "SELECT order_id orderId, create_time createTime, state, total_count totalCount,total_amount totalAmount,"
				+ "user_id userId FROM bs_order WHERE user_id = ?";
		
		return getBeanList(Order.class, sql, userId);
	}
	
	@Override
	public int saveOrder(Order order) {
		String sql = "INSERT INTO bs_order(order_id, create_time, state, total_count,"
				+ "total_amount,user_id) VALUES(?, ?, ?, ?, ?, ?)";
		
		return update(sql,order.getOrderId() , order.getCreateTime() , order.getState() , order.getTotalCount() ,order.getTotalAmount(), order.getUserId() );
	}




} 
