package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	//1.保存订单项到数据库的方法
	int saveOrderItem(OrderItem item);
}
