package com.jim.util;

public class log {
    boolean isPrint;

    public log(boolean b) {
	isPrint = b;
    }

    public void p(String s) {
	if (isPrint)
	System.out.println(s);
    }
}
