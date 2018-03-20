package cn.wp.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import cn.wp.domain.User;
import cn.wp.web.form.SiginFrom;

public class WebUtils {
	public static<T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			//创建封装数据的bean
			T bean = beanClass.newInstance();
			//把数据整合到bean中
			Enumeration enumeration =  request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void copyBean(Object form, Object user) {

		// TODO 自动生成的方法存根
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
				if (value==null) {
					return null;
				}
				String string = (String)value;
				if (string.trim().equals("")) {
					return null;
				}
				SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				 try {
					return dfDateFormat.parse(string);
				} catch (ParseException e) {
					new RuntimeException(e);
				}
				return null;
			}
		}, Date.class);
		
		try {
			BeanUtils.copyProperties(user, form);
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}
}
