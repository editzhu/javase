package com.jim.mypush;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void send() {
	String mail = "";
	String content = "";
	for (int i = 0; i < DataBase.infoList.size(); i++) {
	    // System.out.println(DataBase.infoList.get(i));
	    if (!DataBase.infoListSend.contains(DataBase.infoList.get(i).url)) {// 检测url在已发送列表中是否存在
		mail = DataBase.infoList.get(i).mail;
		content += DataBase.infoList.get(i).content + "\n";
		content += "价格" + DataBase.infoList.get(i).price + "元\n";
		content += DataBase.infoList.get(i).url + "\n";
		content += "----------------------------------\n";
		DataBase.infoListSend.add(DataBase.infoList.get(i).url);// 插入到已发送列表
	    }
	}
	System.out.println("准备发送邮件");
	if (!("".equals(mail)))
	    sendTxtMail(mail, content);// 发送邮件
    }

    public static void sendTxtMail(String email, String content) {
	Properties props = new Properties();

	Session session = Session.getInstance(props, null);
	session.setDebug(true);// 打开debug模式，会打印发送细节到console
	Message message = new MimeMessage(session); // 实例化一个MimeMessage集成自abstract
						    // Message 。参数为session
	try {
	    message.setFrom(new InternetAddress("editzhu@163.com")); // 设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]

	    message.setText(content); // 设置文本内容
				      // 单一文本使用setText,Multipart复杂对象使用setContent

	    message.setSubject("你的东西到了"); // 设置标题

	    message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 设置接收方

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
}
