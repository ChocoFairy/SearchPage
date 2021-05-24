package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import javaBean.Admin;
import javaBean.Person;
import utils.JDBCUtils;

/*
 * 用于管理员对学生信息进行增，删，改
 */
public class ChangeInfoDao {
	
	/*
	 * 删除某个学生信息
	 */
	public boolean deleteInfoById(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from duters where id=?";
		int row = qr.update(sql, id);
		if(row>0) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * 修改某个学生信息
	 */
	public void changeInfo(Person p) throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update duters set name=?, phone=?, qq=?, email=? where id=?";
		Object[] params = {p.getName(), p.getPhone(), p.getQq(), p.getEmail(), p.getId()};
		int row = qr.update(sql, params);
		System.out.println("修改："+row);
	}
	
	/*
	 * 修改某个管理员的信息 
	 */
	public void changeAdminInfo(Admin admin) throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update admin set name=?, realName=?, password=?, email=?, isSign=? where id=?";
		Object[] params = {admin.getName(), admin.getRealName(), admin.getPassword() , admin.getEmail(), admin.getIsSign() ,admin.getId()};
		int row = qr.update(sql, params);
		System.out.println("修改："+row);
	}
	
	/*
	 * 添加学生信息
	 */
	public boolean addNewInfo(Person p) throws SQLException{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into duters (id, name, phone, qq, email) values (?,?,?,?,?)";
		Object[] params = {p.getId(), p.getName(), p.getPhone(), p.getQq(), p.getEmail()};
		int row = qr.update(sql, params);
		if(row>0) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * 给老师投票
	 */
	public void voteTeacherById(int id, int votes) throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update teachers set votes=? where id=?";
		Object[] params = {votes, id};
		int row = qr.update(sql, params);
		System.out.println("修改："+row);
	}
}
