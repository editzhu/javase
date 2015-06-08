package com.jim.callback;

public class CallBack01 {
    static class Student {
	// doHomeWork是回调函数
	public void doHomeWork(String homework, String answer) {
	    System.out.println("作业本");
	    if (answer != null) {
		System.out.println("作业:" + homework + " 答案:" + answer);
	    } else {
		System.out.println("作业:" + homework + " 答案:" + "空白");
	    }
	}
    }

    static class RoomMate {
	// 库函数
	public void getAnswer(String homework, Student student) {
	    if ("1+1=?".equals(homework)) {
		student.doHomeWork(homework, "2");
	    } else {
		student.doHomeWork(homework, "(空白)");
	    }
	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();
	Student student = new Student();

	String aHomeworkString = "1+1=?";

	// 主函数调用库函数,库函数调用回调函数
	roomMate.getAnswer(aHomeworkString, student);

    }
}
