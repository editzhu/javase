package com.jim.thread;

public class MyThread {

    public static void main(String[] args) {

	class Gr implements Runnable {
	    private String str;

	    Gr(String c) {
		str = c;
	    }

	    public void run() {
		System.out.println("name is : " + str);
	    }
	}
	Gr gr = new Gr("jim");
	Thread t1 = new Thread(gr);
	t1.start();
	System.out.println("this is main thread");
    }
}
