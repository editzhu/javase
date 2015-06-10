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
	    if (!DataBase.infoListSend.contains(DataBase.infoList.get(i).url)) {// ���url���ѷ����б����Ƿ����
		mail = DataBase.infoList.get(i).mail;
		content += DataBase.infoList.get(i).content + "\n";
		content += "�۸�" + DataBase.infoList.get(i).price + "Ԫ\n";
		content += DataBase.infoList.get(i).url + "\n";
		content += "----------------------------------\n";
		DataBase.infoListSend.add(DataBase.infoList.get(i).url);// ���뵽�ѷ����б�
	    }
	}
	System.out.println("׼�������ʼ�");
	if (!("".equals(mail)))
	    sendTxtMail(mail, content);// �����ʼ�
    }

    public static void sendTxtMail(String email, String content) {
	Properties props = new Properties();

	Session session = Session.getInstance(props, null);
	session.setDebug(true);// ��debugģʽ�����ӡ����ϸ�ڵ�console
	Message message = new MimeMessage(session); // ʵ����һ��MimeMessage������abstract
						    // Message ������Ϊsession
	try {
	    message.setFrom(new InternetAddress("editzhu@163.com")); // ���÷�����,ʹ��setXXX���õ��û���ʹ��addXXX���InternetAddress[]

	    message.setText(content); // �����ı�����
				      // ��һ�ı�ʹ��setText,Multipart���Ӷ���ʹ��setContent

	    message.setSubject("��Ķ�������"); // ���ñ���

	    message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // ���ý��շ�

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
}
