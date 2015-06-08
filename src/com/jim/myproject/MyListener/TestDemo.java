package com.jim.myproject.MyListener;

public class TestDemo {
    DemoSource ds;

    public TestDemo() {
	try {
	    ds = new DemoSource();
	    // �����������¼�Դ�����еǼ�:
	    DemoListener1 listener1 = new DemoListener1();
	    ds.addDemoListener(listener1);
	    ds.addDemoListener(new DemoListener() {
		@Override
		public void handleEvent1(DemoEvent event) {
		    System.out.println("Method come from ������");
		}
	    });
	    ds.nodifyDemoEvent();// �����¼�,֪ͨ������
	} catch (Exception exception) {
	}
    }

    public static void main(String[] args) {
	new TestDemo();
    }
}
