package cn.wp.web.controller;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wp.domain.User;
import cn.wp.exception.UserExitsException;
import cn.wp.service.impl.BusnessServiceImpl;
import cn.wp.utils.TokenProcessor;
import cn.wp.utils.WebUtils;
import cn.wp.web.form.SiginFrom;

public class CheckInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		SiginFrom form = WebUtils.request2Bean(request,SiginFrom.class);
		//获取token值
		String r_token = request.getParameter("token");
		//获取session，不存在不新建
		HttpSession session = request.getSession(false);
		//判断条件是否成立
		//System.out.println(r_token);
		if (!(r_token != null && session != null && r_token.equalsIgnoreCase((String) session.getAttribute("token")))) {
			request.setAttribute("message","请勿重复提交");
			// 每一次image请求对应一个session值
			// 产生随机数（表单号)
			TokenProcessor tp = TokenProcessor.getInstance();
			String token = tp.generateToken();
			// 获取session并添加随机数
			request.getSession().setAttribute("token", token);
			request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			return;
		}
		//验证码校验
		String result = (String)session.getAttribute("image");
		if (result!=null&&!result.isEmpty()) {
			if (!result.equals(request.getParameter("checkimg"))) {
				form.getErros().put("checkimg", "验证码错误");
				request.setAttribute("form", form);
				request.getRequestDispatcher("/sigin").forward(request, response);
				return;
			}
		}
		//System.out.println("bir"+form.getBirthday());
		//如果校验失败跳回表单页面，回显失败原因
		if (!form.validate()) {
			request.setAttribute("form",form);
			request.getRequestDispatcher("/sigin").forward(request, response);
			return;
		}
		//校验成功调用service处理注册请求
		User user =new User();
		WebUtils.copyBean(form,user);
		BusnessServiceImpl ser = new BusnessServiceImpl();
		try {
			ser.regidter(user);
			//如果注册成功发送成功消息
			request.setAttribute("message","注册成功");
			request.getSession().setAttribute("user",user);
			request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			return;
		} catch (UserExitsException e) {
			//如果serivce处理不成功，则跳回注册页面，显示注册用户存在
			form.getErros().put("username", "用户已经注册");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/sigin").forward(request, response);
			return;
		}catch (Exception e) {
			//如果serivce处理不成功，且为其他原因，显示全局友好提示
			request.setAttribute("message","服务器出现未知问题");
			request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			return;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
