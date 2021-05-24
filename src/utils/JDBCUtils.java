package utils;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCUtils {
	
	//创建BasicDataSource对象
	private static BasicDataSource dataSource = new BasicDataSource();
	
	//自定义配置连接池
	static {
		dataSource.setDriverClassName(JDBCUtilsConfig.getDriverClass());
		dataSource.setUrl(JDBCUtilsConfig.getUrl());
		dataSource.setUsername(JDBCUtilsConfig.getUserName());
		dataSource.setPassword(JDBCUtilsConfig.getPassWord());
		//对象连接池中连接数量的配置
		dataSource.setInitialSize(10);    //初始化连接数
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(1);
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
}
