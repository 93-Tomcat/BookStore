package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	//处理收货的业务方法
	boolean takeGoods(String orderId, int state);
	//查询用户订单集合的方法
	List<Order> findMyOrders(int userId);
	
	//创建订单的业务方法，返回的是订单编号
	String createOrder(Cart cart,User user);
}
