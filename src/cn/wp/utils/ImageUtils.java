package cn.wp.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//返回一个随机image
public class ImageUtils{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 120;
	private static final int HEIGHT = 25;    
	private String result="";
	
	
	public Image getImage() throws ServletException, IOException {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		//背景色
		setBackground(graphics);
		//设置边框
		setBorder(graphics);
		//画干扰线
		setLine(graphics);
		//写随机数
		setNumber((Graphics2D)graphics);
		//图形给浏览器
		return image;
	}
	private void setNumber(Graphics2D graphics) {
		// TODO 自动生成的方法存根
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("宋体",Font.BOLD,20));
		//汉字[\u4e00-\u9fa5]
		for (int i = 0; i < 5; i++) {
			int x = new Random().nextInt(10);
			result += x;
			graphics.drawString(x+"",WIDTH - i*25,HEIGHT-2);
		}
		
		
	}
	public String getResult() {
		
	    if(result == null || result.length() == 1){
	        return null;
	      }
	      char[] ch = result.subSequence(1, result.length()).toString().toCharArray();//字符串转换成字符数组
	      int len = 4; 
	      for(int i= 0; i< len/ 2; i++) { 
	        ch[i]^= ch[len- 1- i]; 
	        ch[len- 1- i]^= ch[i]; 
	        ch[i]^= ch[len- 1- i]; 
	      }
	      return new String(ch);
	}
	
	private void setLine(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		
		for(int i = 0;i <5;i++){
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);

		}
		
	}
	private void setBorder(Graphics graphics) {
		// TODO 自动生成的方法存根
		graphics.setColor(Color.BLUE);
		graphics.drawRect(1, 1, WIDTH-2, HEIGHT-2);
	}

	private void setBackground(Graphics graphics) {
		// TODO 自动生成的方法存根
		graphics.setColor(Color.WHITE);
		//填充矩形
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
	}

}
