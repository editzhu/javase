package com.jim.myproject.MyListener;

//�¼��������ӿ�
public interface DemoListener extends java.util.EventListener {
    // EventListener �������¼��������ӿڱ�����չ�ı�ǽӿ�,
    // ��Ϊ���������ݵı�ǽӿ�,�����¼��Ĵ������������Լ�����������:
    public void handleEvent1(DemoEvent dm);
}
