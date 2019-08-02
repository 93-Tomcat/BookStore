package com.atguigu.bookstore.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 订单相关操作必须登陆后才可以使用
 *  	-因为订单和用户相关联
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	
	//处理收货请求的方法
	protected void takeGoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//用户收货本质将订单state改为2
		int state = 2;
		String orderId = request.getParameter("orderId");
		//调理业务层处理收货业务
		boolean b = service.takeGoods(orderId, state);
		//跳转回之前页面
		response.sendRedirect(request.getHeader("referer"));
		//修改order.jsp页面确认收货的超链接  点击提交请求给当前方法处理
	}
	
	//处理用户查询自己订单的请求
	protected void findMyOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取session中的user对象 判断是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
			//调用业务层查询订单集合
			List<Order> list = service.findMyOrders(user.getId());
			//存到域中
			request.setAttribute("list", list);
			//转发到/pages/order/order.jsp显示订单集合数据
			request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
			//去order.jsp页面遍历 订单集合显示
			//修改user_header已登录的状态栏  我的订单超链接 点击提交请求给当前方法
		
	}
	
	//处理用户结账的请求
	protected void checkOut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//1.获取session中的user对象，  判断是否登录
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
			//已登录  调用业务层处理业务
			Cart cart = (Cart)session.getAttribute("cart");
			String orderId = service.createOrder(cart, user);
			//3.跳转到/pages/cart/checkout.jsp 给用户显示结账成功的订单id、
			//不建议使用转发  会造成表单重复提交 导致订单创建多次
			session.setAttribute("orderId", orderId);
			response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
			//4.修改/pages/cart/checkout.jsp页面获取订单id进行显示
			//5.修改cart.jsp页面，去结账超链接 点击访问此方法
		
	}
}
