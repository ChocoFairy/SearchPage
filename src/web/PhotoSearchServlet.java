 package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai.FaceSearch;
import dao.GetUserDao;
import javaBean.Person;

@WebServlet("/photoSearchServlet")
public class PhotoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoSearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String filePath = request.getParameter("filePath");
		
		if(filePath!=null&&!filePath.equals("")) {
			System.out.println(filePath);
			// 根据图片路径调用人脸搜索api获得匹配结果
			String result = FaceSearch.search(filePath);
			System.out.println("图片搜索结果："+result);
			if(result!=null&&!result.contains("pic not has face")) {
				int start = result.indexOf("user_id")+10;
				int end = result.indexOf("user_info")-3;
				result = result.substring(start, end);
				
				GetUserDao userDao = new GetUserDao();
				Person p = null;
				try {
					List<Person> duters = userDao.getDuters();
					for(Person temp:duters) {
						if(temp.getId().equals(result.trim())) {
							p = temp;
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(p!=null) {
					request.getSession().setAttribute("photoSearchPerson", p);
					// 转发到jsp视图显示
				    request.getRequestDispatcher("/photoSearchResult.jsp").forward(request, response);
				}
				
//				response.setContentType("text/html;charset=utf-8");
//				response.getWriter().append(result);
				
			}else {
				System.out.println("查无此人");
				request.getSession().setAttribute("ResultList", null);
				request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}