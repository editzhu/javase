package com.jim.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyJmail {
    public static void sendTxtMail() {
	Properties props = new Properties();

	Session session = Session.getInstance(props, null);
	session.setDebug(true);// ��debugģʽ�����ӡ����ϸ�ڵ�console
	Message message = new MimeMessage(session); // ʵ����һ��MimeMessage������abstract
						    // Message ������Ϊsession
	try {
	    message.setFrom(new InternetAddress("editzhu@163.com")); // ���÷�����,ʹ��setXXX���õ��û���ʹ��addXXX���InternetAddress[]

	    message.setText("ֻ��һ����򵥵����ı�����Ӵ��"); // �����ı�����
						// ��һ�ı�ʹ��setText,Multipart���Ӷ���ʹ��setContent

	    message.setSubject("ֻ�Ǽ�򵥵����ı�����Ӵ��"); // ���ñ���

	    message.setRecipient(Message.RecipientType.TO, new InternetAddress("yinyong@utry.cn")); // ���ý��շ�

	    /**
	     * ʹ�þ�̬����ÿ�η�����Ҫ����һ����smtp�����������ӣ�������ֶ���������״̬
	     * ��ͨ��session���tansport�����ӵ�mailserver
	     * ����session�Ϳ���ʹ��Session.getDefaultInstance(props,null);���
	     */
	    Transport transport = session.getTransport("smtp");
	    transport.connect("smtp.163.com", "editzhu", "aa123123");
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	} catch (AddressException e) {
	    // �˴�����AddressException�쳣 [The exception thrown when a wrongly
	    // formatted address is encountered.]

	} catch (MessagingException e) {
	    // �˴�����MessagingException�쳣 [The base class for all exceptions
	    // thrown by the Messaging classes ]
	}

    }

    public static void main(String[] args) {
	sendTxtMail();
    }
}
