package com.jim.myproject.MyListener;

import java.util.ArrayList;
import java.util.Iterator;

//定义事件源对象（事件源相当于单击按钮事件当中的按钮对象、属于被监听者）：
public class DemoSource {
    private ArrayList<DemoListener> repository = new ArrayList<DemoListener>();// 监听自己的监听器队列

    public DemoSource() {
    }

    public void addDemoListener(DemoListener dl) {
	repository.add(dl);
    }

    public void nodifyDemoEvent() {// 通知所有的监听者
	Iterator<DemoListener> it = repository.iterator();
	while (it.hasNext()) {
	    DemoListener dl = it.next();
	    dl.handleEvent1(new DemoEvent(this));

	}
    }
}
