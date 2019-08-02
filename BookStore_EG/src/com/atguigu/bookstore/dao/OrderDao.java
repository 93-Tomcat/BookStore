package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao {
	//3.更新订单状态的方法
	int updateOrderStateByOrderId(String orderId , int state);
	
	//2.查询用户订单集合的方法
	List<Order> getOrdersByUserId(int userId);
	
	//1.保存订单到数据库方法
	int saveOrder(Order order);
}
