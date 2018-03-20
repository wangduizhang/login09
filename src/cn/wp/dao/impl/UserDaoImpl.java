package cn.wp.dao.impl;


import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.wp.dao.UserDao;
import cn.wp.domain.User;
import cn.wp.utils.XMLUtils;

public class UserDaoImpl implements UserDao{
	//add注册用户
	public  void add(User user){
		try {
			Document document = XMLUtils.getDocument();
			Element root = document.getRootElement();
			Element tag = root.addElement("user");
			tag.addAttribute("name", user.getUsername());
			tag.addAttribute("password", user.getPassword());
			tag.addAttribute("email", user.getEmail());
			tag.addAttribute("birthday", user.getBirthday()==null?"":user.getBirthday().toLocaleString());
			tag.addAttribute("nickname", user.getNickname());
			XMLUtils.write2XML(document);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			throw new RuntimeException(e); 
		}
	}
	//find
	public  User find(String userName,String userPassword){
		try {
			
			Document document = XMLUtils.getDocument();
			Element element =(Element) document.selectSingleNode("//user[@name='"+userName+"' and @password='"+userPassword+"']");
			if (element==null) {
				//System.out.println("["+userName+"]"+"["+userPassword+"]");
				return null;
			}
			User user = new User();
			user.setUsername(element.attributeValue("name"));
			user.setPassword(element.attributeValue("password"));
			user.setEmail(element.attributeValue("email"));
			user.setNickname(element.attributeValue("nickname"));
			String date  = element.attributeValue("birthday");
			if (date==null||date.isEmpty()) {
				user.setBirthday(null);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(format.parse(date));
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//查找用户是否存在
	public  boolean isHave(String username){
		try {
			Document document = XMLUtils.getDocument();
			Element element = (Element) document.selectSingleNode("//user[@name='"+username+"']");
			if (element==null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
