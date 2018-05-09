package com.java.test;

public class test {
    public static void main(String[] args) {
	Runnable1 r = new Runnable1();
	Thread t = new Thread(r);
	t.start();
	for (int i = 0; i < 30; i++) {
	    System.out.println("main:" + i);
	}
    }
}

class Runnable1 implements Runnable {
    public void run() {
	for (int i = 0; i < 1000000; i++) {
	    if (i > 100000)
		i = 0;
	}
    }
}
