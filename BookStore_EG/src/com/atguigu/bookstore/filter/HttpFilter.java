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
/**
 * HttpFilter的封装思想：
 * 		1、创建自定义类HttpFilter  实现Filter接口
 *		2、将子类不需要实现的方法实现
 *		3、先根据doFilter方法创建一个重载的方法，参数类型修改为Http类型的
 *			在生命周期方法doFilter中将参数类型强转为Http类型的，并调用重载方法
 *			将子类必须实现的方法（重载doFilter）改为抽象的	
 */
public abstract class HttpFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public  void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		//参数类型转换
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//调用重载的方法：目的为了让自定义的重载方法变得和生命周期方法doFilter一样(每次有请求访问目标资源时都会被调用)
		doFilter(req, res, chain);
	}
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;
	@Override
	public void destroy() {
		
	}

}
