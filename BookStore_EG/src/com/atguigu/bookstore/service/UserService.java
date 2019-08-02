package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

public interface UserService {
	boolean checkUsername(String username);//处理检查用户名的业务，返回true可用
	User doLogin(User loginUser);//处理登录的业务
	boolean doRegist(User registUser);//处理注册的业务
}
