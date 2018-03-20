package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.junit.Test;

import cn.wp.domain.User;
import cn.wp.exception.UserExitsException;
import cn.wp.service.impl.BusnessServiceImpl;

public class ServiceTest {

	private static BusnessServiceImpl bus = new BusnessServiceImpl();
	//@Test
	public void signtest() {
		User user = new User();
		user.setUsername("wp2");
		user.setPassword("3991");
		user.setEmail("326013443@qq.com");
		user.setNickname("pp");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(format.parse("1996-08-17"));
			//System.out.println(user.getBirthday());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			bus.regidter(user);
		} catch (UserExitsException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void logintest() {
		User user = bus.login("wp", "3991");
		if (user ==null) {
			System.out.println("fail");
			return;
		}
		System.out.println("success");
	}
	

}
