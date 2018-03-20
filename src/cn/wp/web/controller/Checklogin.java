package cn.wp.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wp.dao.impl.UserDaoImpl;
import cn.wp.domain.User;
import cn.wp.utils.ServiceUtils;
import cn.wp.utils.TokenProcessor;
import cn.wp.utils.WebUtils;
import cn.wp.web.form.SiginFrom;

/**
 * Servlet implementation class Checklogin
 */
public class Checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checklogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		request.setCharacterEncoding("UTF-8");

		//获取token值
		String r_token = request.getParameter("token");
		//获取session，不存在不新建
		HttpSession session = request.getSession(false);
		//判断条件是否成立
		if (!(r_token != null && session != null && r_token.equalsIgnoreCase((String) session.getAttribute("token")))) {
			// 每一次image请求对应一个session值
			// 产生随机数（表单号)
			TokenProcessor tp = TokenProcessor.getInstance();
			String token = tp.generateToken();
			// 获取session并添加随机数
			request.getSession().setAttribute("token", token);
			request.setAttribute("message","请勿重复提交");
			request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			return;
		}
		//验证码校验
		String result = (String)session.getAttribute("image");
		if (result!=null&&!result.isEmpty()) {
			if (!result.equals(request.getParameter("checkimg"))) {
				request.setAttribute("message","验证码错误");
				//request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
		}
		//账号密码检验
		String username = request.getParameter("username");
		String password = ServiceUtils.md5(request.getParameter("password"));
		if (username==null||password==null||username.isEmpty()||password.isEmpty()) {
			//request.setAttribute("message","验证码错误");
			//request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		User user = userDaoImpl.find(username, password);
		if (user==null) {
			request.setAttribute("message","用户不存在或密码错误");
			//request.getRequestDispatcher("/WEB-INF/web/message.jsp").forward(request, response);
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}else{
			request.getSession().setAttribute("user",user);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
