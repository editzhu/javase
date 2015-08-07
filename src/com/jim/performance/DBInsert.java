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
	// ����Connection����
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
	// �ж����ݿ������Ƿ�Ϊ��
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

    // ͨ�������ж���
    public static Connection getConn() throws SQLException, IOException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�������ע�ᵽ����������
	String url = "jdbc:mysql://localhost:3306/test"; // ���ݿ������ַ���
	String username = "root"; // ���ݿ��û���
	String password = "123456"; // ���ݿ�����
	return DriverManager.getConnection(url, username, password);

    }

    // ͨ�������ļ�������
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
	// ����������ַ���
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
