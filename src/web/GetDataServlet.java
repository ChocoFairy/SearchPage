package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaBean.Person;
import utils.FindTool;

@WebServlet("/getDataServlet")
public class GetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int totalNum;
	public int pageNum;

	public GetDataServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		data = data.trim();
		request.getSession().setAttribute("Data", data);
		
		List<Person> persons = FindTool.find(data);
		if(persons!=null) {
			//存在查询结果
			totalNum = persons.size();
			request.getSession().setAttribute("Num", totalNum);
			request.getSession().setAttribute("Persons", persons);
			request.getServletContext().getRequestDispatcher("/showResult.jsp?currentPageNum=0&totalNum="+totalNum).forward(request, response);
		}else {
			//查询结果为空
			request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
		}
		
		
/*		// 检查data的格式是否符合搜索规范
		if(data.startsWith("id ")) {
			String id = data.substring(data.indexOf(" ")+1);
			Person person = FindTool.searchId(id);
			if(person!=null) {
				List<Person> persons = new ArrayList<Person>();
				persons.add(person);
				request.getSession().setAttribute("Num", 1);
				request.getSession().setAttribute("Persons", persons);
				request.getServletContext().getRequestDispatcher("/showResult.jsp").forward(request, response);
			}else {
				// 查询结果为空
				request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
			}
			
		}else if(data.startsWith("name ")) {
			// 支持模糊查询
			String name = data.substring(data.indexOf(" ")+1);
			List<Person> persons = FindTool.searchName(name);
			if(persons!=null) {
				int num = persons.size();
				
				System.out.println("一共查询到"+num+"人");
				request.getSession().setAttribute("Num", num);
				request.getSession().setAttribute("Persons", persons);
				request.getServletContext().getRequestDispatcher("/showResult.jsp").forward(request, response);
			}else {
				// 查询结果为空
				request.getServletContext().getRequestDispatcher("/badResult.jsp").forward(request, response);
			}
			
			
		}else if(data.startsWith("tel ")) {
			String phone = data.substring(data.indexOf(" ")+1);
			Person person = FindTool.searchPhone(phone);
			if(person!=null) {
				List<Person> persons = new ArrayList<Person>();
				persons.add(person);
				request.getSession().setAttribute("Num", 1);
				request.getSession().setAttribute("Persons", persons);
				request.getServletContext().getRequestDispatcher("/showResult.jsp").forward(request, response);
			}else {
				// 查询结果为空
				request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
			}
		}else if(data.startsWith("qq ")) {
			String qq = data.substring(data.indexOf(" ")+1);
			Person person = FindTool.searchQQ(qq);
			if(person!=null) {
				List<Person> persons = new ArrayList<Person>();
				persons.add(person);
				request.getSession().setAttribute("Num", 1);
				request.getSession().setAttribute("Persons", persons);
				request.getServletContext().getRequestDispatcher("/showResult.jsp").forward(request, response);
			}else {
				// 查询结果为空
				request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
			}
		}else if(data.startsWith("email ")) {
			String email = data.substring(data.indexOf(" ")+1);
			Person person = FindTool.searchEmail(email);
			if(person!=null) {
				List<Person> persons = new ArrayList<Person>();
				persons.add(person);
				request.getSession().setAttribute("Num", 1);
				request.getSession().setAttribute("Persons", persons);
				request.getServletContext().getRequestDispatcher("/showResult.jsp").forward(request, response);
			}else {
				// 查询结果为空
				request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
			}
		}else {
			// 不符合规范，转发警告并重新输入
			request.getServletContext().getRequestDispatcher("/warning.html").forward(request, response);
		}
		*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}