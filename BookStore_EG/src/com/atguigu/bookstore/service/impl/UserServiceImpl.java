package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public User doLogin(User loginUser) {
		return dao.getUserByUsernameAndPassword(loginUser);
	}

	@Override
	public boolean doRegist(User registUser) {
		return dao.saveUser(registUser)>0;
	}

	@Override
	public boolean checkUsername(String username) {
		// 调用dao在数据库中检查用户名是否存在
		User user = dao.getUserByUsername(username);//如果返回user不为空，用户名不可用  返回flase
		return user==null;
	}

}
