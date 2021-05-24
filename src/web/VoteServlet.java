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
import dao.GetUserDao;
import javaBean.Teacher;

@WebServlet("/voteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VoteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String param = request.getParameter("select");
		if(param!=null&&!param.equals("")) {
			int id = Integer.parseInt(param);
			GetUserDao teacherDao = new GetUserDao();
			try {
				List<Teacher> teachers = teacherDao.getTeachers();
				for(Teacher t: teachers) {
					if(t.getId()==id) {
						// 数据库中改变
						ChangeInfoDao changeDao = new ChangeInfoDao();
						changeDao.voteTeacherById(id, t.getVotes()+1);
						break;
					}
				}
				request.getRequestDispatcher("/successVote.html").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}