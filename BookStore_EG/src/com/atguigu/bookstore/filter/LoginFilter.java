package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 拦截访问OrderServlet的请求
 * 	配置文件路径 <servlet-name>OrderServlet</servlet-name>
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取session中的user对象 判断是否登录
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if(user==null) {
					//没有登陆  跳转到登陆页面让用户登陆
					request.setAttribute("errorMsg", "订单相关操作必须登陆！");
					request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
				}else {
					//已登录  放行请求
					chain.doFilter(request, response);
				
				}
		
	}

   
}
