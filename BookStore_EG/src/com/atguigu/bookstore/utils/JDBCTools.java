package com.atguigu.bookstore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCTools {
	
	static {
		//使用当前类的类加载器读取类路径下的配置文件
		InputStream is = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties info = new Properties();
		try {
			info.load(is);//自动解析properties文件的流，以键值对的形式存到properties集合中
			
			//初始化连接池对象:由于druid数据库连接池在获取数据时使用的key和我们自己的不一样，需要给durid提供key对应的参数
			source = DruidDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//一个项目使用一个连接池
	private static DataSource source;
	private static Map<Thread , Connection> map = new ConcurrentHashMap<Thread , Connection> ();
	//1、获取数据库连接的方法：Connection
	public static Connection getConn() {
		//2、获取连接对象
		Connection conn = map.get(Thread.currentThread());
		if(conn==null) {
			//如果没有绑定，当前线程对象就是第一次获取数据库连接，新创建并绑定
			try {
				conn = source.getConnection();
				map.put(Thread.currentThread(), conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//2、关闭资源的方法： ResultSet 、 Statement(preparedStatement) 、 Connection
	public static void closeConn(ResultSet rs , Statement  sm , Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sm!=null) {
			try {
				sm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				//如果是从连接池中获取的连接 关闭时是将连接还给连接池
				//动态代理：从连接池中获取的连接是druid代理过的对象，关闭时有durid来管理
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void closeConn(Connection conn1) {
		Connection conn = map.get(Thread.currentThread());
		if(conn!=null) {
			try {
				//如果是从连接池中获取的连接 关闭时是将连接还给连接池
				//动态代理：从连接池中获取的连接是druid代理过的对象，关闭时有durid来管理
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//移除map中之前绑定的数据
		map.remove(Thread.currentThread());
		
	}
}
