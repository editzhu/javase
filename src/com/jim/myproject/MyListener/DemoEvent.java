package com.jim.myproject.MyListener;

//定义事件（状态）对象（该事件对象包装了事件源对象、作为参数传递给监听器、很薄的一层包装类）：
public class DemoEvent extends java.util.EventObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DemoEvent(Object source) {
	super(source);// source-事件源对象-如在界面上发生的点击按钮事件中的按钮
	// 所有Event在构造时都引用了对象"source",在逻辑上认为该对象是最初发生有关Event的对象
    }

    public void say1() {
	System.out.println("This is say method...");
    }
}
