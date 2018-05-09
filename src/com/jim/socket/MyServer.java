package com.jim.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String args[]) throws IOException {
	// Ϊ�˼���������е��쳣��Ϣ��������
	int port = 8899;
	// ����һ��ServerSocket�����ڶ˿�8899��
	ServerSocket server = new ServerSocket(port);
	while (true) {
	    // server���Խ�������Socket����������server��accept����������ʽ��
	    Socket socket = server.accept();
	    if (socket == null)
		break;
	    new Thread(new Task(socket)).start();

	}
	server.close();
    }

    static class Task implements Runnable {
	private Socket socket;

	public Task(Socket socket) {
	    this.socket = socket;
	}

	public void run() {
	    try {
		handleSocket();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	private void handleSocket() throws IOException {

	    // ���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�
	    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    StringBuilder sb = new StringBuilder();
	    String temp;
	    int index;
	    while ((temp = br.readLine()) != null) {
		if ((index = temp.indexOf("eof")) != -1) {// ����eofʱ�ͽ�������
		    // �����кܴ������,��eof���ָ�Ϊ2����ͬ�İ�ʱ,�������޷�����������
		    sb.append(temp.substring(0, index));
		    break;
		}
		sb.append(temp);
	    }
	    System.out.println("from client: " + sb);
	    Writer writer = new OutputStreamWriter(socket.getOutputStream());
	    writer.write("Hello Client.");
	    writer.write("eof\n");
	    writer.flush();
	    writer.close();
	    br.close();
	    socket.close();

	}
    }
}
