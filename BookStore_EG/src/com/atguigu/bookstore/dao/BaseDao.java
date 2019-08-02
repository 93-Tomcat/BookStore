package com.atguigu.bookstore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCTools;
import com.atguigu.bookstore.utils.JDBCUtils;


public class BaseDao {
	/*
	 * 创建queryrunner：提供增删改查的实现方法
	 */
	 private QueryRunner runner = new QueryRunner();
	
	/*
	 * 1.通用的增删改
	 * - 返回值如果大于0操作成功
	 */
	public int update(String sql, Object...params) {
		Connection conn = JDBCTools.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return i;
	}
	
	/*
	 * 2.查询一条记录 并封装为一个对象
	 */
	public <T>T getBean(Class<T> type,String sql,Object...params) {
		Connection conn = JDBCTools.getConn();
		T t = null;
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return t;
	}
	
	/*
	 * 3.查询多条记录封装为多个对象集合
	 */
	public <T>List<T> getBeanList(Class<T> type,String sql,Object...params) {
		Connection conn = JDBCTools.getConn();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	
	/**
	 * 4、查询表的记录总条数(分页需要使用 )
	 *  select count(1) from bs_user;
	 */
	public int getCount(String sql , Object...params) {
		Connection conn = JDBCTools.getConn();
		Integer count = 0;
		try {
			//查询数量时，mysql默认将数字以Long类型返回
			long l = (long) runner.query(conn, sql, new ScalarHandler(), params);
			count = (int)l;
		} catch (SQLException e) {
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return count;
		
	}
	
	/*
	 * 5.批量增删改的方法
	 */
	public void batchUpdate(String sql,Object[][] parmas) {
		Connection conn = JDBCTools.getConn();
		try {
			runner.batch(conn, sql, parmas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//将异常转为运行时异常抛出
			throw new RuntimeException(e);
		}
	}
	
}
