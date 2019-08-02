package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCTools;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//将每一个请求看作一个事务，服务器处理每个请求时会使用一个线程对象处理
		//1.获取数据库连接
		//2.将连接对象和线程对象绑定：保证线程执行的所有的代码 如果需要使用数据库连接  使用的是同一个
		Connection conn = JDBCTools.getConn();//获取当前线程对象绑定的数据库连接 如果没有新创建并绑定
		
		//线程对象在经过的代码都可以获取到，可以作为键，数据库连接可以作为值
		
		//3.开启事务
		try {
			conn.setAutoCommit(false);
			//4.放行请求  让后续代码继续执行 （执行多个sql操作）
			chain.doFilter(request, response);
			//5.根据事务执行的过程判断事务提交还是回滚
			//5.1代码如果能执行到这一步，没有异常  提交
			conn.commit();
		} catch (Exception e) {
			//5.2 有异常  回滚
			try {
				conn.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			//给用户错误提示：编写错误页面
			response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
		}finally {
			//6.关闭资源  关闭连接  释放线程绑定的数据
			JDBCTools.closeConn(conn);
		}
	
	}

  
}
