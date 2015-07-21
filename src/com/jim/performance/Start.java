package com.jim.performance;

import java.util.HashMap;
import java.util.Map;

public class Start implements Runnable {
    private ThreadID var;
    public static final int ThreadCount = 10;
    static Map<Integer, Long> m = new HashMap<Integer, Long>();
    private static long start;

    Start(ThreadID v) {
	this.var = v;
    }

    public void run() {
	print("var getThreadID =" + var.getThreadID());
	Start.m.put(var.getThreadID(), (long) 0);
	long duration = Request.get();
	Start.m.put(var.getThreadID(), duration);
    }

    private static void print(String msg) {
	String name = Thread.currentThread().getName();
	System.out.println(name + ": " + msg);
    }

    public static void main(String[] args) {
	start = System.currentTimeMillis();
	ThreadID tid = new ThreadID();
	Start c = new Start(tid);
	Thread[] t = new Thread[ThreadCount];
	for (int i = 0; i < ThreadCount; i++) {
	    t[i] = new Thread(c);
	    t[i].start();
	}
	while (true) {
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    int count = 0;
	    int finish = 0;
	    int fail = 0;

	    for (Map.Entry<Integer, Long> entry : m.entrySet()) {
		if (entry.getValue() == 0)
		    count++;
		if (entry.getValue() > 0) {
		    finish++;
		}
		if (entry.getValue() < 0) {
		    fail++;
		}
	    }
	    System.out.println("finish=" + finish + " count=" + count + " fail=" + fail + " m:" + m);
	    if (finish >= ThreadCount)
		break;
	}
	System.out.println("total:" + (System.currentTimeMillis() - start));
    }

}

class ThreadID extends ThreadLocal<Object> {
    private int nextID;

    public ThreadID() {
	nextID = 10001;
    }

    private synchronized Integer getNewID() {
	Integer id = new Integer(nextID);
	nextID++;
	return id;
    }

    protected Object initialValue() {
	print("in initialValue()");
	return getNewID();
    }

    public int getThreadID() {
	Integer id = (Integer) get();
	return id.intValue();
    }

    private static void print(String msg) {
	String name = Thread.currentThread().getName();
	System.out.println(name + ": " + msg);
    }
}
