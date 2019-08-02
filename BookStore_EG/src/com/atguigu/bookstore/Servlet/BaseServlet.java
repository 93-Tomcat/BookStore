package com.atguigu.bookstore.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 为了管理子类方法调用
 * 1.以后其他的Servlet都继承BaseServlet
 * 		目的：当访问Servlet时，服务器调用其生命周期方法，当掉用到doGet时，我们希望进入父类的doGet方法控制子类方法的调用
 * 2.访问BaseServlet的子类时，必须提供一个参数：type=要调用的方法名
 * 3.以后子类Servlet中处理请求方法请求参数必须是HttpServletRequest request，HttpServletResponse response
 * 4.如果访问子类Servlet出现405的错误
 * 	代表当前的请求方式子类Servlet没有实现doGet或doPost方法，被HttpServlet的doGet或doPost处理了请求
 * 		解决：要么自己实现doGet或doPost要么  继承BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//1.获取调用当前doGet方法的对象类型
		//this代表调用此方法的对象：以后访问哪个Servlet进入到此方法this就代表该Servlet
		Class<? extends BaseServlet> clazz = this.getClass();
		//2.获取要调用的方法名
		String type = request.getParameter("type");
		//3。在类中根据方法名和形参类型列表确定唯一一个方法
		
		try {
			Method 	method = clazz.getDeclaredMethod(type, HttpServletRequest.class,HttpServletResponse.class);
			//4.调用此方法
			method.invoke(this, request,response);
		} catch (Exception e) {

			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
