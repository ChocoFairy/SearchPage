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

@WebServlet("/deleteInfoServlet")
public class DeleteInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("del_id");
		//System.out.println(id);
		// 通过Dao层在数据库中进行修改
		ChangeInfoDao changeDao = new ChangeInfoDao();
		boolean flag = false;
		try {
			flag = changeDao.deleteInfoById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag) {
			// 数据库中的已经删掉了，只要更新一下ResultList就行了，不用再从数据库中获取
			List<Person> list = (List<Person>) request.getSession().getAttribute("ResultList");
			for(Person p: list) {
				if(id.equals(p.getId())) {
					list.remove(p);
					break;
				}
			}
			request.getSession().setAttribute("ResultList", list); 
			// 获取当前页码
			int pageNum = (int) request.getSession().getAttribute("current_page");
			request.getRequestDispatcher("/infoManager.jsp?currentPageNum="+pageNum).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}