package com.atguigu.bookstore.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoImplTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testSaveUser() {
		//模仿用户注册
		//1.用户在页面中填写注册信息提交给服务器
		String username = "admin";
		String password = "123456";
		String email = "admin@atguigu.vip.com";
		//2.将参数封装为user对象，方便传参数
		User registUser = new User(null,username,password,email);
		//3.调用UserDao将user保存到数据库中
		int i = dao.saveUser(registUser);
		//4.判断处理结果
		System.out.println(i>0?"注册成功！":"注册失败！");
	}
	
	@Test
	public void testLogin() {
		//模拟登陆
		//1.用户在页面中填写登陆信息提交给服务器
		String username = "admin1";
		String password = "123456";
		User loginUser = new User(null,username,password,null);
		//2.调用UserDAO处理登录请求
		User user = dao.getUserByUsernameAndPassword(loginUser);
		//user是查询道德包含user的所有信息的对象，loginUser用来封装登陆参数的对象
		System.out.println(user);
	}
}
