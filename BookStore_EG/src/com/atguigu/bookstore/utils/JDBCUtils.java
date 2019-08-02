package com.atguigu.bookstore.utils;

/**
 * 管理数据库连接的工具类
 * @author Administrator
 *
 */


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	//使用数据库连接池管理数据库连接
	private static DataSource source;
	
	//通过静态代码块初始化数据库连接池
	static {
		//在创建数据库连接池之前 提供properties 携带数据库连接池需要的初始化参数
		Properties info = new Properties();
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			info.load(is);
			source = DruidDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取数据库连接方法
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 * 释放连接的方法
	 */
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
