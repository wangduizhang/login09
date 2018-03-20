package cn.wp.web.form;

import java.util.HashMap;
import java.util.Map;


import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class SiginFrom {
	private String username;
	private String password;
	private String password2;
	private String email;
	private String birthday;
	private String nickname;
	private String checkimg;
	public String getCheckimg() {
		return checkimg;
	}
	public void setCheckimg(String checkimg) {
		this.checkimg = checkimg;
	}
	private Map<String, String> erros = new HashMap<String, String>();	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public Map<String, String> getErros() {
		return erros;
	}
	public void setErros(Map<String, String> erros) {
		this.erros = erros;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String date) {
		this.birthday = date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean validate(){
		boolean isOK = true;
		//用户名
		if (this.username==null||username.isEmpty()) {
			isOK=false;
			erros.put("username","用户名不能为空");
		}else{
			if (!this.username.matches("^[A-Za-z]{3,8}")) {
				isOK=false;
				erros.put("username","用户名三到八位字母");
			}
		}
		//密码
		if (this.password==null||this.password.isEmpty()) {
			isOK=false;
			erros.put("password","密码不能为空");
		}else {
			if (!this.password.equals(this.password2)) {
				isOK=false;
				erros.put("password","两次密码要一致");
			}
		}
		//邮箱
		if (this.email==null||this.email.isEmpty()) {
			isOK=false;
			erros.put("email","邮箱不能为空");
		}else {
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOK=false;
				erros.put("email","邮箱格式不正确");
			}
		}
		//生日
		if (this.birthday==null||this.birthday.isEmpty()) {
			isOK=false;
			erros.put("birthday","生日不能为空");
		}else {
			try {
				DateLocaleConverter dlc= new DateLocaleConverter();
				dlc.convert(this.birthday);
			} catch (Exception e) {
				isOK=false;
				erros.put("birthday","生日格式不对");
			}
		}
		if (this.nickname==null||this.nickname.isEmpty()) {
			isOK=false;
			erros.put("nickname","昵称不能为空");
		}else {
			if (!this.nickname.matches("[\u4e00-\u9fa5]+")) {
				isOK=false;
				erros.put("nickname","昵称必须为中文");
			}	
		}
		if (this.checkimg==null||this.checkimg.isEmpty()) {
			isOK=false;
			erros.put("checkimg","验证码不能为空");
		}
		return isOK;
	}
}
