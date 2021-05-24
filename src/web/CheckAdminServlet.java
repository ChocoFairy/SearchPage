package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChangeInfoDao;
import dao.GetUserDao;
import javaBean.Admin;
import javaBean.Person;

@WebServlet("/checkAdminServlet")
public class CheckAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从Dao层到数据库中获得admin信息
		GetUserDao userDao = new GetUserDao();
		ChangeInfoDao changeDao = new ChangeInfoDao();
		List<Admin> admins = null;
		List<Person> duters = null;
		try {
			admins = userDao.getAdmin();
			// 继续从Dao层获得所有学生信息
			duters = userDao.getDuters();
			// 利用容器类自动排序
			Collections.sort(duters);  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(admins==null) {
			System.out.println("数据库出现问题！");
			request.getRequestDispatcher("/index.html").forward(request, response);
		}else {
			/*
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			if(name.equals(admin.getName())&&password.equals(admin.getPassword())) {
				System.out.println("成功登陆！");
				if(duters!=null) {
					request.getSession().setAttribute("ResultList", duters);
				}
				request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
			}else {
				//检查是不是个人登陆的普通用户-假设用邮箱来作为密码
				boolean flag = false;
				for(Person p:duters) {
					if(name.equals(p.getId())&&password.equals(p.getEmail())) {
						System.out.println("成功登陆！");
						// 将数据库中isSign设置为1
						
						flag = true;
						request.getSession().setAttribute("currentUser", p);
						request.getRequestDispatcher("/commonUser.jsp").forward(request, response);
					}
				}
				if(!flag) {
					System.out.println("用户名或密码不正确！");
					request.getRequestDispatcher("/loginFailed.html").forward(request, response);
				}
			}*/
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			// 检查是不是管理员
			boolean flag = false;
			for(Admin admin:admins) {
				if(name.equals(admin.getName())&&password.equals(admin.getPassword())) {
					// 检查是否重复登陆
					/*if(admin.getIsSign()==1) {
						System.out.println("不允许重复登陆！");
						request.getRequestDispatcher("/loginFailed.html").forward(request, response);
						break;
					}*/
					System.out.println("成功登陆！");
					// 加载所有duters
					if(duters!=null) {
						request.getSession().setAttribute("ResultList", duters);
					}
					// 将数据库中isSign设置为1，防止重复登陆
					admin.setIsSign(1);
					try {
						changeDao.changeAdminInfo(admin);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					// 将当前登陆用户的name传入session
					request.getSession().setAttribute("currID", admin.getName());
					flag = true;
					request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
				}
			}
			if(!flag) {
				// 检查是不是个人登陆的普通用户-假设用邮箱来作为密码
				boolean flag2 = false;
				for(Person p:duters) {
					if(name.equals(p.getId())&&password.equals(p.getEmail())) {
						System.out.println("成功登陆！");
						flag2 = true;
						request.getSession().setAttribute("currentUser", p);
						request.getRequestDispatcher("/commonUser.jsp").forward(request, response);
					}
				}
				if(!flag2) {
					System.out.println("用户名或密码不正确！");
					request.getRequestDispatcher("/loginFailed.html").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
