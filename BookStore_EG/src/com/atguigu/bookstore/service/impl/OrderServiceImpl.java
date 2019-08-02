package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	
	@Override
	public boolean takeGoods(String orderId, int state) {
		
		return orderDao.updateOrderStateByOrderId(orderId, state)>0;
	}
	
	@Override
	public List<Order> findMyOrders(int userId) {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByUserId(userId);
	}
	
	@Override
	public String createOrder(Cart cart, User user) {
		//1. 将购物车转为订单对象，并和user关联
		//设置order需要的数据
		//1.1订单id：时间戳+userId
		String orderId = System.currentTimeMillis()+""+user.getId();
		//1.2订单创建时间：当前时间
		Date createTime = new Date();
		//1.3订单转台：0默认未发货
		int state = 0;
		Order order = new Order(orderId, cart.getTotalCount(), cart.getTotalAmount(), createTime, state, user.getId());
		//1.4保存订单到数据库
		orderDao.saveOrder(order);
		
		//2.将购物车的购物项集合转为一个个的订单项对象，存到数据库并和order关联
		List<CartItem> list = cart.getCartItemList();
		//遍历购物项集合
		for (CartItem cartItem : list) {
			Book book = cartItem.getBook();
			//一个购物项对应一个订单项
			OrderItem orderItem = new OrderItem(null, book.getTitle(), book.getAuthor(), book.getImgPath(), book.getPrice(), cartItem.getCount(), cartItem.getAmount(), orderId);
			//将订单项存到数据库
			orderItemDao.saveOrderItem(orderItem);
			//3.修改订单项对应的图书销量库存
			//修改book对象的销量和库存
			book.setSales(book.getSales()+cartItem.getCount());//之前的销量+该购物项的数量
			book.setStock(book.getStock()-cartItem.getCount());//之前的库存-该购物项的数量
			bookDao.updateBookById(book);
		}
		
		
		//4.清空购物车
		cart.clearCart();
		//5.返回订单号
		return orderId;
	}




}
