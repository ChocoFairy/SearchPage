package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChangeInfoDao;
import javaBean.Person;

@WebServlet("/addInfoServlet")
public class AddInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("add_id");
		if(id==null||id.equals("")) {
			request.getRequestDispatcher("/id_null.html").forward(request, response);
		}else {
			String name = request.getParameter("add_name");
			String phone = request.getParameter("add_phone");
			String qq = request.getParameter("add_qq");
			String email = request.getParameter("add_email");
			
			Person p = new Person(id,name,phone,qq,email);
			System.out.println(p);
			// 通过Dao层向数据库中添加一个新的学生（在此之前判断了id非空，id唯一在数据库中自己判断——抛出异常）
			ChangeInfoDao changeDao = new ChangeInfoDao();
			boolean flag = false;
			try {
				flag = changeDao.addNewInfo(p);
			} catch (SQLException e) {
				// id主键冲突
				request.getRequestDispatcher("/id_null.html").forward(request, response);
				e.printStackTrace();
				return;
			}
			if(flag) {
				List<Person> list = (List<Person>) request.getSession().getAttribute("ResultList");
				list.add(p);
				Collections.sort(list);
				request.getSession().setAttribute("ResultList", list);
				// 获取当前页码
				int pageNum = (int) request.getSession().getAttribute("current_page");
				request.getRequestDispatcher("/infoManager.jsp?currentPageNum="+pageNum).forward(request, response);			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}