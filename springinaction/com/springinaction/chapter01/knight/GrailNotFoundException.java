package com.springinaction.chapter01.knight;

public class GrailNotFoundException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GrailNotFoundException(String message) { // 用来创建指定参数对象
	super(message);
    } // 调用超类构造器
}
