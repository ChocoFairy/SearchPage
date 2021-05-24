package utils;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/*
 * 读取db.properities配置文件的工具类
 */
public class JDBCUtilsConfig {
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		try {
			readConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void readConfig() throws Exception{
		//读取db.properities文件
		InputStream ins = JDBCUtilsConfig.class.getClassLoader().getResourceAsStream("db.properties");
		Properties pro = new Properties();
		pro.load(ins);
		//获取集合中的键值对
		driverClass = pro.getProperty("driverClass");
		url = pro.getProperty("url");
		username = pro.getProperty("username");
		password = pro.getProperty("password"); 
	}
	
	public static String getDriverClass() {
		return driverClass;
	}
	public static String getUrl() {
		return url;
	}
	public static String getUserName() {
		return username;
	}
	public static String getPassWord() {
		return password;
	}

}
