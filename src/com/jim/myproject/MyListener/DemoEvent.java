package com.jim.myproject.MyListener;

//�����¼���״̬�����󣨸��¼������װ���¼�Դ������Ϊ�������ݸ����������ܱ���һ���װ�ࣩ��
public class DemoEvent extends java.util.EventObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DemoEvent(Object source) {
	super(source);// source-�¼�Դ����-���ڽ����Ϸ����ĵ����ť�¼��еİ�ť
	// ����Event�ڹ���ʱ�������˶���"source",���߼�����Ϊ�ö�������������й�Event�Ķ���
    }

    public void say1() {
	System.out.println("This is say method...");
    }
}
