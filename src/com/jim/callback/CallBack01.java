package com.jim.callback;

public class CallBack01 {
    static class Student {
	// doHomeWork�ǻص�����
	public void doHomeWork(String homework, String answer) {
	    System.out.println("��ҵ��");
	    if (answer != null) {
		System.out.println("��ҵ:" + homework + " ��:" + answer);
	    } else {
		System.out.println("��ҵ:" + homework + " ��:" + "�հ�");
	    }
	}
    }

    static class RoomMate {
	// �⺯��
	public void getAnswer(String homework, Student student) {
	    if ("1+1=?".equals(homework)) {
		student.doHomeWork(homework, "2");
	    } else {
		student.doHomeWork(homework, "(�հ�)");
	    }
	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();
	Student student = new Student();

	String aHomeworkString = "1+1=?";

	// ���������ÿ⺯��,�⺯�����ûص�����
	roomMate.getAnswer(aHomeworkString, student);

    }
}
