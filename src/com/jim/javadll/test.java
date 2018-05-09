package com.jim.javadll;

import java.io.UnsupportedEncodingException;

public class test {
    static {
	System.loadLibrary("demo");// 载入dll
    }

    // 用native关键字修饰将被其它语言实现的方法
    public native static void ini();// 函数声明

    public native static String LPR(String s);// 函数声明

    public native static int add(int a, int b);// 函数声明

    public static void main(String[] args) throws UnsupportedEncodingException {
	// 本地方法的调用
	System.out.println("ini\n");
	ini();
	System.out.println("lpr\n");
	String s = LPR(".\\car1.jpg");
	System.out.println("xxxyyy : " + s);
    }

}
