package com.jim.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyClient {
    public static void main(String args[]) throws Exception {
	// Ϊ�˼���������е��쳣��ֱ��������
	String host = "127.0.0.1"; // Ҫ���ӵķ����IP��ַ
	int port = 8899; // Ҫ���ӵķ���˶�Ӧ�ļ����˿�
	// �����˽�������
	Socket client = new Socket(host, port);
	// �������Ӻ�Ϳ����������д������
	Writer writer = new OutputStreamWriter(client.getOutputStream());
	writer.write("���,������");
	writer.write("eof\n");
	writer.flush();
	// д���Ժ���ж�����
	BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	// ���ó�ʱ��Ϊ1��
	client.setSoTimeout(1 * 1000);
	StringBuffer sb = new StringBuffer();
	String temp;
	int index;
	try {
	    while ((temp = br.readLine()) != null) {
		if ((index = temp.indexOf("eof")) != -1) {
		    sb.append(temp.substring(0, index));
		    break;
		}
		sb.append(temp);
	    }
	} catch (SocketTimeoutException e) {
	    System.out.println("���ݶ�ȡ��ʱ��");
	}
	System.out.println("from server: " + sb);
	writer.close();
	br.close();
	client.close();
    }
}
