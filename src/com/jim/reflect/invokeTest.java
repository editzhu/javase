package com.jim.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class invokeTest {
    public void a(String s, String l) {
	System.out.println(s);
    }

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
	Class<?> clazz = invokeTest.class;
	invokeTest it = new invokeTest();
	Method method = clazz.getMethod("a", String.class, String.class);
	// Class.forName("invoke").getMethod("a").invoke(clazz.newInstance());
	// method.invoke(clazz.newInstance(), "adfasdf");
	method.invoke(it, "adfasdf", "dddd");
    }
}
