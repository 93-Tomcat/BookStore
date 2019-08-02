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
 * 处理用户登陆请求的Servlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao dao = new UserDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User loginUser = new User(null, username, password, null);
		
		//2.调用其它类处理业务
		User user = dao.getUserByUsernameAndPassword(loginUser);
		
		//3.根据处理结果给用户响应
		if(user == null) {
			//设置错误信息在域中共享
			request.setAttribute("errorMsg", "账号或者密码错误！");
			//查询失败  登陆失败，转发到Login.html让用户继续登陆,转发会造成转发后的页面中的相对路径失效
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登陆成功 重定向到成功页面
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
