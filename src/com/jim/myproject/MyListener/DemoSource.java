package com.jim.myproject.MyListener;

import java.util.ArrayList;
import java.util.Iterator;

//�����¼�Դ�����¼�Դ�൱�ڵ�����ť�¼����еİ�ť�������ڱ������ߣ���
public class DemoSource {
    private ArrayList<DemoListener> repository = new ArrayList<DemoListener>();// �����Լ��ļ���������

    public DemoSource() {
    }

    public void addDemoListener(DemoListener dl) {
	repository.add(dl);
    }

    public void nodifyDemoEvent() {// ֪ͨ���еļ�����
	Iterator<DemoListener> it = repository.iterator();
	while (it.hasNext()) {
	    DemoListener dl = it.next();
	    dl.handleEvent1(new DemoEvent(this));

	}
    }
}
