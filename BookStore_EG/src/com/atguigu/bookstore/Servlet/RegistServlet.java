package com.atguigu.bookstore.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

/**
 * Servlet:单列多线程，使用到变量的值如果涉及到多个线程使用可能被修改，可能会有线程安全问题
 * 处理用户注册请求的Servlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao dao = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		//将参数封装成对象
		User registUser = new User(null, username, password, email);
		//2.调用其它类处理业务逻辑
		int i = dao.saveUser(registUser);
		//3.根据处理结果给用户响应
		if(i>0) {
			//注册成功 重定向到成功页面
			//重定向的绝对路径有浏览器解析，基准地址到服务器(http://localhost:8080)
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else {
			//注册失败，设置错误消息
			request.setAttribute("errorMsg", "用户名已存在！");
			//request.getRequestDispatcher("pages/user/regist.html").forward(request,response);
			//转发的绝对路径有服务器解析，基准地址到项目(http://localhost:8080/BookStore_EG01)
			//转发会造成相对路径失效
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
