package cn.wp.web.UI;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wp.utils.ImageUtils;
import cn.wp.utils.TokenProcessor;

/**
 * Servlet implementation class ChaeckImg
 */
public class ChaeckImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChaeckImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ImageUtils imageUtils = new ImageUtils();
		Image image = imageUtils.getImage();
		ImageIO.write((RenderedImage)image,"jpg",response.getOutputStream());
		request.getSession().setAttribute("image",imageUtils.getResult());
		//System.out.println(imageUtils.getResult());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
