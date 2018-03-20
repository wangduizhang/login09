package cn.wp.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wp.utils.TokenProcessor;

/**
 * 为用户提供注册页面
 */
public class SiginUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiginUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//每一次image请求对应一个session值
		// 产生随机数（表单号)
		TokenProcessor tp = TokenProcessor.getInstance();
		String token = tp.generateToken();
		//获取session并添加随机数
		request.getSession().setAttribute("token",token);
		
		String referer=request.getHeader("referer");
		if(referer==null || !referer.startsWith("http://localhost:8080"))
		{
		    response.sendRedirect("index.jsp");
		    return;
		}
		request.getRequestDispatcher("/WEB-INF/web/sigin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
