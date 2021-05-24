package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javaBean.Admin;
import javaBean.Person;
import javaBean.Teacher;
import utils.JDBCUtils;

/*
 * 用于获取数据库中的用户信息（管理员，学生信息）
 */
public class GetUserDao {
	private List<Admin> admins;
	private List<Person> duters;
	private List<Teacher> teachers;
	
	/*
	 * 从数据库中获得admin信息，进行管理员校验
	 */
	public List<Admin> getAdmin() throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 通过select 查询获得结果
		String sql = "select * from admin";
		admins = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
		// 检查结果
		//System.out.println(admin);
		return admins;
	}
	
	/*
	 * 获得所有Duters的信息，进行搜索与管理
	 */
	public List<Person> getDuters() throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from duters";
		duters = qr.query(sql, new BeanListHandler<Person>(Person.class));
		// 检查结果
//		for(Person p: duters) {
//			System.out.println(p);
//		}
		//System.out.println("数据库中一共有"+duters.size()+"条学生信息");
		return duters;
	}
	
	/*
	 * 获得所有老师的信息
	 */
	public List<Teacher> getTeachers() throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from teachers";
		teachers = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class));
		//System.out.println("数据库中一共有"+teachers.size()+"条老师信息");
		return teachers;
	}
}
