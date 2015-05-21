package com.jim.myproject.MyListener;

public class DemoListener1 implements DemoListener {
    @Override
    public void handleEvent1(DemoEvent de) {
	System.out.println("Inside listener1...");
	de.say1();// »Øµ÷
    }
}
