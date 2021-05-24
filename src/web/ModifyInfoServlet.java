package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChangeInfoDao;
import javaBean.Person;

@WebServlet("/modifyInfoServlet")
public class ModifyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		
		Person p = new Person(id,name,phone,qq,email);
		//System.out.println(p);
		ChangeInfoDao changeDao = new ChangeInfoDao();
		try {
			changeDao.changeInfo(p);
			List<Person> list = (List<Person>) request.getSession().getAttribute("ResultList");
			for(Person person:list) {
				if(person.getId().equals(p.getId())) {
					person.setName(name);
					person.setPhone(phone);
					person.setQq(qq);
					person.setEmail(email);
					break;
				}
			}
			request.getSession().setAttribute("ResultList", list);    
			// 获取当前页码
			int pageNum = (int) request.getSession().getAttribute("current_page");
			request.getRequestDispatcher("/infoManager.jsp?currentPageNum="+pageNum).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}