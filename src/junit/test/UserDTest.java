package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import cn.wp.dao.impl.UserDaoImpl;
import cn.wp.domain.User;

public class UserDTest {

	private static UserDaoImpl userdaoimpl = new UserDaoImpl();
	
	@Test
	public void findtest(){
		User user = userdaoimpl.find("wp", "3991");
		System.out.println(user);
	}
	@Test
	public void idHavetest(){
		System.out.println(userdaoimpl.isHave("wp"));
	}
	@Test
	public void  addtest(){
		User user = new User();
		user.setUsername("pl");
		user.setPassword("123");
		user.setEmail("dsafas");
		user.setNickname("ddd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(format.parse("2017-10-12"));
			System.out.println(user.getBirthday());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		userdaoimpl.add(user);
	}

}
