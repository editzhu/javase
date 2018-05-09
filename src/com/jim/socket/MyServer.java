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
	// 为了简单起见，所有的异常信息都往外抛
	int port = 8899;
	// 定义一个ServerSocket监听在端口8899上
	ServerSocket server = new ServerSocket(port);
	while (true) {
	    // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
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

	    // 跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
	    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    StringBuilder sb = new StringBuilder();
	    String temp;
	    int index;
	    while ((temp = br.readLine()) != null) {
		if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
		    // 这里有很大的隐患,当eof被分隔为2个不同的包时,会遇到无法结束的现象
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
