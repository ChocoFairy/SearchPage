package javaBean;

import java.io.Serializable;

public class Admin implements Serializable{
	private int id;            // 编号-主键
	private String name;       // 用户名-英文
	private String realName;   // 真实姓名-中文
	private String password;   // 登陆密码
	private String email;      // 校验邮箱
	private int isSign;
	private int isAdmin;
	private String userID;
	
	public Admin() {}

	public Admin(int id, String name, String realName, String password, String email, int isSign, int isAdmin,
			String userID) {
		super();
		this.id = id;
		this.name = name;
		this.realName = realName;
		this.password = password;
		this.email = email;
		this.isSign = isSign;
		this.isAdmin = isAdmin;
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", realName=" + realName + ", password=" + password + ", email="
				+ email + ", isSign=" + isSign + ", isAdmin=" + isAdmin + ", userID=" + userID + "]";
	}

	

}
