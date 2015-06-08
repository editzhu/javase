package com.jim.callback;

import java.util.concurrent.TimeUnit;

//将CallBack02的例子更改为异步调用的形式
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
			    someone.doHomeWork(homework, "不存在这个解,0不能当除数");
			} catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

		    } else {
			someone.doHomeWork(homework, "(空白)");
		    }
		}
	    }).start();

	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();

	// 主函数调用库函数,库函数调用回调函数
	roomMate.getAnswer("0/0=?", new doHomeWork() {
	    @Override
	    public void doHomeWork(String homework, String answer) {
		// TODO Auto-generated method stub
		System.out.println("作业本");
		if (answer != null) {
		    System.out.println("作业:" + homework + " 答案:" + answer);
		} else {
		    System.out.println("作业:" + homework + " 答案:" + "空白");
		}
	    }
	});
	roomMate.getAnswer("1+1=?", new doHomeWork() {
	    @Override
	    public void doHomeWork(String homework, String answer) {
		// TODO Auto-generated method stub
		System.out.println("作业本");
		if (answer != null) {
		    System.out.println("作业:" + homework + " 答案:" + answer);
		} else {
		    System.out.println("作业:" + homework + " 答案:" + "空白");
		}
	    }
	});

    }
}
