package com.jim.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc {
    static log log = new log(true);

    public static void ini() {
	// ����Connection����
	Connection conn = null;
	Statement stat = null;
	// �ж����ݿ������Ƿ�Ϊ��

	try {
	    conn = getConn1();
	    if (conn != null) {
		log.p("���ݿ����ӳɹ���"); // ���������Ϣ
	    } else {
		log.p("���ݿ�����ʧ�ܣ�"); // ���������Ϣ
	    }
	    stat = conn.createStatement();
	    String query = "select * from tm_tie";
	    ResultSet rs = stat.executeQuery(query);
	    while (rs.next()) {
		log.p(rs.getString(1));
		log.p(rs.getString(2));
		log.p(rs.getString(3));
		log.p(rs.getString(4));
		log.p(rs.getString(5));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {

	    try {
		conn.close();
		stat.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    // ͨ�������ж���
    public static Connection getConn() throws SQLException, IOException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�������ע�ᵽ����������
	String url = "jdbc:mysql://localhost:3306/liuyanban"; // ���ݿ������ַ���
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

    public static void main(String[] args) {
	ini();
    }
}
