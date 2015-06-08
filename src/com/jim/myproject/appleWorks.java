package com.jim.myproject;

/*
 * ���̵߳���ϰ
 * ģ��ƻ��԰�Ĺ��˽�ƻ���ɼ������ƻ����
 * ���˿Ϳ������߿��е�ƻ��
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

// ƻ����
class kuang {
    private final static int RONGLIANG = 6;
    private static int height = 0;

    synchronized boolean putOne() {
	try {
	    while (height == RONGLIANG) {
		System.out.println("������,�Ҽ�������ƻ���ȴ�,ֱ��������λ��");

		this.wait();

	    }
	    this.notifyAll();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	height++;
	System.out.println("������һ��ƻ��,ƻ�����й���ƻ������:" + height);
	return true;
    }

    synchronized boolean getOne() {
	try {
	    while (height == 0) {
		System.out.println("����û��ƻ����,�ò���,�Ҽ����ȴ�ֱ��������ƻ��");

		this.wait(1000);

	    }
	    this.notifyAll();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	height--;
	System.out.println("������һ��ƻ��,ƻ�����й���ƻ������:" + height);

	return true;
    }
}
