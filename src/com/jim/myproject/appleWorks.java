package com.jim.myproject;

/*
 * 多线程的练习
 * 模拟苹果园的工人将苹果采集后放入苹果筐
 * 而顾客可以拿走框中的苹果
 */
public class appleWorks {
    public static void main(String[] args) {
	gongren r = new gongren();
	guke g = new guke();
	Thread t1 = new Thread(r);
	Thread t2 = new Thread(r);
	Thread t3 = new Thread(r);
	t1.start();
	t2.start();
	t3.start();
	Thread guke1 = new Thread(g);
	guke1.start();
    }
}

class gongren implements Runnable {
    private kuang k = new kuang();

    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep((int) (Math.random() * 1000));
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    k.putOne();
	}
    }
}

class guke implements Runnable {
    private kuang k = new kuang();

    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep((int) (Math.random() * 400));
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    k.getOne();
	}
    }
}

// 苹果筐
class kuang {
    private final static int RONGLIANG = 6;
    private static int height = 0;

    synchronized boolean putOne() {
	try {
	    while (height == RONGLIANG) {
		System.out.println("框满了,我继续拿着苹果等待,直到框里有位置");

		this.wait();

	    }
	    this.notifyAll();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	height++;
	System.out.println("放入了一个苹果,苹果框中共有苹果个数:" + height);
	return true;
    }

    synchronized boolean getOne() {
	try {
	    while (height == 0) {
		System.out.println("框中没有苹果了,拿不了,我继续等待直到里面有苹果");

		this.wait(1000);

	    }
	    this.notifyAll();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	height--;
	System.out.println("拿走了一个苹果,苹果框中共有苹果个数:" + height);

	return true;
    }
}
