package com.jim.callback;

//将CallBack01的例子更改为接口形式
public class CallBack02 {
    static interface doHomeWork {
	void doHomeWork(String homework, String answer);
    }

    static class RoomMate {
	// 库函数
	public void getAnswer(String homework, doHomeWork someone) {
	    if ("1+1=?".equals(homework)) {
		someone.doHomeWork(homework, "2");
	    } else if ("0/0=?".equals(homework)) {

	    } else {
		someone.doHomeWork(homework, "(空白)");
	    }
	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();

	// 主函数调用库函数,库函数调用回调函数
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
