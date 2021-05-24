package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetUserDao;
import javaBean.Person;

@WebServlet("/searchInfoServlet")
public class SearchInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("search_id");
		String name = request.getParameter("search_name");
		String phone = request.getParameter("search_phone");
		String qq = request.getParameter("search_qq");
		
		GetUserDao userDao = new GetUserDao();
		List<Person> duters = null;
		try {
			duters = userDao.getDuters();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(id==null&&name==null&&phone==null&&qq==null) {  // 回到最初状态
			request.getSession().setAttribute("ResultList", duters);
			request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
		}else {
			if(id.equals("")&&name.equals("")&&phone.equals("")&&qq.equals("")) { // 回到最初状态
				request.getSession().setAttribute("ResultList", duters);
				request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
			}else {
				// 依次查询
				if(id!=null&&!id.equals("")&&duters!=null) {   // id其实可以唯一搜索，但若后面的姓名输错也没有结果
					// 注意：ArrayList循环删除时不能用forEach，否则容易抛出ConcurrentModifiedException
					Iterator<Person> it = duters.iterator();
					while(it.hasNext()) {
						Person p = it.next();
						if(!p.getId().contains(id)) {
							it.remove();
						}
					}
				}
				if(name!=null&&!name.equals("")&&duters!=null) {   // 姓名支持模糊搜索
					Iterator<Person> it = duters.iterator();
					while(it.hasNext()) {
						Person p = it.next();
						if(!p.getName().contains(name)) {
							it.remove();
						}
					}
				}
				if(phone!=null&&!phone.equals("")&&duters!=null) {
					Iterator<Person> it = duters.iterator();
					while(it.hasNext()) {
						Person p = it.next();
						if(!p.getPhone().contains(phone)) {
							it.remove();
						}
					}
				}
				if(qq!=null&&!qq.equals("")&&duters!=null) {
					Iterator<Person> it = duters.iterator();
					while(it.hasNext()) {
						Person p = it.next();
						if(!p.getQq().contains(qq)) {
							it.remove();
						}
					}
				}
				request.getSession().setAttribute("ResultList", duters);
				request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}