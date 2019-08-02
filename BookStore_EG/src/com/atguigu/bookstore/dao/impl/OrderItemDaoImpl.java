package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem item) {
		String sql = "INSERT INTO bs_orderitem(title , author, price , img_path, amount, count,"
				+ "order_id) VALUES(?,?,?,?,?,?,?)";
		
		return update(sql,item.getTitle() , item.getAuthor() , item.getPrice() , item.getImgPath() , item.getAmount(),
				item.getCount() , item.getOrderId());
	}

}
