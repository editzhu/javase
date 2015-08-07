package com.jim.performance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import com.jim.util.ConnectionPool;
import com.jim.util.log;

public class DBInsert {
    static log log = new log(true);
    static ConnectionPool connPool;

    static void ini() {
	connPool = new ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "root", "123456");
	try {
	    connPool.createPool();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public static long get() {
	long start = System.currentTimeMillis();
	// 创建Connection连接
	Connection conn = null;
	Statement stat = null;
	try {
	    // conn = getConn();
	    conn = connPool.getConnection();
	    conn.setAutoCommit(true);
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    return -1;
	}
	// 判断数据库连接是否为空
	try {
	    stat = conn.createStatement();
	    String sql = "insert into mytest (content) values ('" + getContent() + "')";
	    stat.executeUpdate(sql);
	    stat.close();

	} catch (Exception e) {
	    e.printStackTrace();
	    return -1;
	} finally {
//	    try {
		// conn.close();
		connPool.returnConnection(conn);
//	    } 
//	    catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return -1;
//	    }
	}
	long end = System.currentTimeMillis();
	return (end - start);
    }

    // 通过程序中定义
    public static Connection getConn() throws SQLException, IOException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动，注册到驱动管理器
	String url = "jdbc:mysql://localhost:3306/test"; // 数据库连接字符串
	String username = "root"; // 数据库用户名
	String password = "123456"; // 数据库密码
	return DriverManager.getConnection(url, username, password);

    }

    // 通过配置文件来设置
    public static Connection getConn1() throws SQLException, IOException {
	Properties props = new Properties();
	FileInputStream in = new FileInputStream("d:/database.properties");
	props.load(in);
	in.close();
	String drivers = props.getProperty("jdbc.drivers");
	if (drivers != null)
	    System.setProperty("jdbc.drivers", drivers);
	String url = props.getProperty("jdbc.url");
	String username = props.getProperty("jdbc.username");
	String password = props.getProperty("jdbc.password");
	log.p(drivers + ":" + url + ":" + username + ":" + password);
	return DriverManager.getConnection(url, username, password);
    }

    public static String getContent() {
	// 产生随机的字符串
	int length = 4000;
	String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	int baseSize = base.length();
	Random random = new Random();
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < length; i++) {
	    int number = random.nextInt(baseSize);
	    sb.append(base.charAt(number));
	}
	for (int i = 0; i < 3; i++) {
	    sb.append(sb);
	}
	return sb.toString();

    }

    public static void main(String[] args) {
	long start = System.currentTimeMillis();
	System.out.println(get());
	long end = System.currentTimeMillis();
	System.out.println(end - start);
    }

}
