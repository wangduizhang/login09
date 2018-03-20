package cn.wp.dao;

import cn.wp.domain.User;

public interface UserDao {

	//add注册用户
	void add(User user);

	//find
	User find(String userName, String userPassword);

	//查找用户是否存在
	boolean isHave(String username);

}