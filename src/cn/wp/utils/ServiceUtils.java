package cn.wp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.*;

public class ServiceUtils {
	public static String md5(String message){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			byte[] md5 = messageDigest.digest(message.getBytes());
			
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
