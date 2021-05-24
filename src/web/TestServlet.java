package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.aip.util.Base64Util;

import ai.AuthService;
import ai.FaceSearch;
import javaBean.Person;
import utils.ImageUtils;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String filePath = request.getParameter("filePath");
		Person currentUser = (Person) request.getSession().getAttribute("currentUser");
		System.out.println(filePath);
		AuthService service = new AuthService();
		String access_token = service.getAuth();
		
		if(filePath==null||filePath.equals("")) {
			
		}else {
			String imgFile = filePath;     // 需要前端传入照片的路径			
			ImageUtils.uploadImg(currentUser.getId(), imgFile);			
			System.out.println("access_token为："+access_token);
			request.getRequestDispatcher("/commonUser.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}