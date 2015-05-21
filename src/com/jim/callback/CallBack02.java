package com.jim.callback;

//��CallBack01�����Ӹ���Ϊ�ӿ���ʽ
public class CallBack02 {
    static interface doHomeWork {
	void doHomeWork(String homework, String answer);
    }

    static class RoomMate {
	// �⺯��
	public void getAnswer(String homework, doHomeWork someone) {
	    if ("1+1=?".equals(homework)) {
		someone.doHomeWork(homework, "2");
	    } else if ("0/0=?".equals(homework)) {

	    } else {
		someone.doHomeWork(homework, "(�հ�)");
	    }
	}
    }

    public static void main(String[] args) {
	RoomMate roomMate = new RoomMate();

	// ���������ÿ⺯��,�⺯�����ûص�����
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
