package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

/**
 * 对bs_user表操作的具体实现
 * 为了方便使用BaseDao中的基本的增删改查的方法，可以继承它
 * @author 93
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUserByUsernameAndPassword(User loginUser) {
		String sql = "SELECT id , username , password , email FROM bs_user "
				+ " WHERE username = ? AND password = ?";
		User user  = getBean(User.class , sql , loginUser.getUsername() , loginUser.getPassword());
		return user;
	}

	@Override
	public int saveUser(User registUser) {
		String sql = "INSERT INTO bs_user(username , password , email ) VALUES(? , ? , ?)";
		int i = 0;
		try {
			i = update(sql, registUser.getUsername(),registUser.getPassword(),registUser.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i ;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT id , username , password , email FROM bs_user "
				+ " WHERE username = ? ";
		User user  = getBean(User.class , sql , username);
		return user;
	}

}
