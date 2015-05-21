package com.jim.myproject.MyListener;

//事件监听器接口
public interface DemoListener extends java.util.EventListener {
    // EventListener 是所有事件监听器接口必须扩展的标记接口,
    // 因为他是无内容的标记接口,所以事件的处理方法由我们自己的声明如下:
    public void handleEvent1(DemoEvent dm);
}
