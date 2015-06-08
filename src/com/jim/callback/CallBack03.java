package com.jim.callback;

import java.util.concurrent.TimeUnit;

//��CallBack02�����Ӹ���Ϊ�첽���õ���ʽ
public class CallBack03 {
    static interface doHomeWork {
	void doHomeWork(String homework, String answer);
    }

    static class RoomMate {

	public void getAnswer(final String homework, final doHomeWork someone) {
	    new Thread(new Runnable() {
		public void run() {
		    if ("1+1=?".equals(homework)) {
			someone.doHomeWork(homework, "2");
		    } else if ("0/0=?".equals(homework)) {
			try {
			    TimeUnit.SECONDS.sleep(5);
			    someone.doHomeWork(homework, "�����������,0���ܵ�����");
			} catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

		    } else {
			someone.doHomeWork(homework, "(�հ�)");
		    }
		}
	    }).start();

	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();

	// ���������ÿ⺯��,�⺯�����ûص�����
	roomMate.getAnswer("0/0=?", new doHomeWork() {
	    @Override
	    public void doHomeWork(String homework, String answer) {
		// TODO Auto-generated method stub
		System.out.println("��ҵ��");
		if (answer != null) {
		    System.out.println("��ҵ:" + homework + " ��:" + answer);
		} else {
		    System.out.println("��ҵ:" + homework + " ��:" + "�հ�");
		}
	    }
	});
	roomMate.getAnswer("1+1=?", new doHomeWork() {
	    @Override
	    public void doHomeWork(String homework, String answer) {
		// TODO Auto-generated method stub
		System.out.println("��ҵ��");
		if (answer != null) {
		    System.out.println("��ҵ:" + homework + " ��:" + answer);
		} else {
		    System.out.println("��ҵ:" + homework + " ��:" + "�հ�");
		}
	    }
	});

    }
}
