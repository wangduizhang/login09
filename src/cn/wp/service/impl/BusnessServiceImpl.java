package cn.wp.service.impl;

import cn.wp.dao.impl.UserDaoImpl;
import cn.wp.domain.User;
import cn.wp.exception.UserExitsException;
import cn.wp.utils.ServiceUtils;

//对web层提供所以服务
public class BusnessServiceImpl {

	private static UserDaoImpl daoImpl = new UserDaoImpl();
	//注册服务
	public  void regidter(User user) throws UserExitsException {
		if (!daoImpl.isHave(user.getUsername())) {
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			daoImpl.add(user);
		}else{
			throw new UserExitsException("用户已存在");
		}
	}
	//登陆服务
	public  User login(String name,String password) {
		return daoImpl.find(name, ServiceUtils.md5(password));
	}
}
