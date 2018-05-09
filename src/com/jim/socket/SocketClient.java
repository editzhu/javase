package com.jim.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    static Socket client;

    public SocketClient(String site, int port) {
	try {
	    client = new Socket(site, port);
	    System.out.println("Client is created! site:" + site + " port:" + port);
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public String sendMsg(String msg) {
	try {
	    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    PrintWriter out = new PrintWriter(client.getOutputStream());
	    out.println(msg);
	    out.flush();
	    return in.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "";
    }

    public void closeSocket() {
	try {
	    client.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {

	SocketClient client = new SocketClient("127.0.0.1", 12345);
	System.out.println(client.sendMsg("aaaa"));
	client.closeSocket();
	System.out.println(client.sendMsg("aaaa5"));
	client.closeSocket();

	SocketClient client1 = new SocketClient("127.0.0.1", 12345);
	System.out.println(client1.sendMsg("bbbb"));
	client1.closeSocket();

	for (int i = 0; i < 100; i++) {
	    SocketClient client2 = new SocketClient("127.0.0.1", 12345);
	    System.out.println(i + ":" + client2.sendMsg(String.valueOf(i)));
	}

    }

}