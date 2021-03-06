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
	session.setDebug(true);// 打开debug模式，会打印发送细节到console
	Message message = new MimeMessage(session); // 实例化一个MimeMessage集成自abstract
						    // Message 。参数为session
	try {
	    message.setFrom(new InternetAddress("editzhu@163.com")); // 设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]

	    message.setText("只是一个简简单单的文本内容哟！"); // 设置文本内容
						// 单一文本使用setText,Multipart复杂对象使用setContent

	    message.setSubject("只是简简单单的文本标题哟！"); // 设置标题

	    message.setRecipient(Message.RecipientType.TO, new InternetAddress("yinyong@utry.cn")); // 设置接收方

	    /**
	     * 使用静态方法每次发送需要建立一个到smtp服务器的链接，你可以手动控制连接状态
	     * ，通过session获得tansport，连接到mailserver
	     * ，而session就可以使用Session.getDefaultInstance(props,null);获得
	     */
	    Transport transport = session.getTransport("smtp");
	    transport.connect("smtp.163.com", "editzhu", "aa123123");
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	} catch (AddressException e) {
	    // 此处处理AddressException异常 [The exception thrown when a wrongly
	    // formatted address is encountered.]

	} catch (MessagingException e) {
	    // 此处处理MessagingException异常 [The base class for all exceptions
	    // thrown by the Messaging classes ]
	}

    }

    public static void main(String[] args) {
	sendTxtMail();
    }
}
