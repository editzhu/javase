package com.jim.util;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * �����ƣ�EmailUtils �������� �ʼ����͹����� �����ˣ�dongjinfeng ����ʱ�䣺2012-7-13 ����12:47:47
 * �޸��ˣ�dongjinfeng �޸�ʱ�䣺2012-7-13 ����12:47:47 �޸ı�ע��
 * 
 * @version 1.0
 * 
 */
public class EmailUtils {
    static class ConfigParamBase {
	static String emailHost = "yinyong@utry.cn";
	static String emailpassword = "123123";
	static String emailFrom = "";
    }

    public static void sendEmail(String email, String title, String content) {
	Properties props = new Properties();
	props.put("mail.host", ConfigParamBase.emailHost);
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.auth", "true"); // ����smtpУ��
	Session sendMailSession = Session.getDefaultInstance(props);

	try {
	    Transport transport = sendMailSession.getTransport("smtp");
	    transport.connect(ConfigParamBase.emailHost, ConfigParamBase.emailFrom.substring(0, ConfigParamBase.emailFrom.indexOf("@")), ConfigParamBase.emailpassword);
	    Message newMessage = new MimeMessage(sendMailSession);

	    // ����mail����
	    String mail_subject = title;
	    newMessage.setSubject(mail_subject);

	    // ���÷����˵�ַ
	    String strFrom = ConfigParamBase.emailFrom;
	    strFrom = new String(strFrom.getBytes(), "ISO-8859-1");
	    newMessage.setFrom(new InternetAddress(strFrom));
	    /*
	     * Address addressFrom[] = { new
	     * InternetAddress("dongjinc_11@163.com") }; //�ı䷢���˵�ַ
	     * newMessage.addFrom(addressFrom);
	     */
	    // �����ռ��˵�ַ
	    Address addressTo[] = { new InternetAddress(email) };
	    newMessage.setRecipients(Message.RecipientType.TO, addressTo);

	    // ����mail����
	    newMessage.setSentDate(new java.util.Date());
	    newMessage.setText(content);

	    newMessage.saveChanges(); // ���淢����Ϣ
	    transport.sendMessage(newMessage, newMessage.getRecipients(Message.RecipientType.TO)); // �����ʼ�

	    transport.close();
	    System.out.println("OK");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void sendHtmlMail(String email, String title, String content) {
	Properties props = new Properties();
	props.put("mail.host", ConfigParamBase.emailHost);
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.auth", "true"); // ����smtpУ��
	Session sendMailSession = Session.getDefaultInstance(props);

	try {
	    Transport transport = sendMailSession.getTransport("smtp");
	    transport.connect("smtp.qq.com", ConfigParamBase.emailFrom.substring(0, ConfigParamBase.emailFrom.indexOf("@")), ConfigParamBase.emailpassword);
	    Message newMessage = new MimeMessage(sendMailSession);

	    // ����mail����
	    String mail_subject = title;
	    newMessage.setSubject(mail_subject);

	    // ���÷����˵�ַ
	    String strFrom = ConfigParamBase.emailFrom;
	    strFrom = new String(strFrom.getBytes(), "ISO-8859-1");
	    newMessage.setFrom(new InternetAddress(strFrom));
	    // �����ռ��˵�ַ
	    Address addressTo[] = { new InternetAddress(email) };
	    newMessage.setRecipients(Message.RecipientType.TO, addressTo);

	    // ����mail����
	    newMessage.setSentDate(new java.util.Date());
	    // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
	    Multipart mainPart = new MimeMultipart();
	    // ����һ������HTML���ݵ�MimeBodyPart
	    BodyPart html = new MimeBodyPart();
	    // ����HTML����
	    html.setContent(content, "text/html; charset=utf-8");
	    mainPart.addBodyPart(html);
	    // ��MiniMultipart��������Ϊ�ʼ�����
	    newMessage.setContent(mainPart);
	    newMessage.saveChanges(); // ���淢����Ϣ
	    transport.sendMessage(newMessage, newMessage.getRecipients(Message.RecipientType.TO)); // �����ʼ�
	    transport.close();
	    System.out.println("OK");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    /**
     * 
     * ������: receiver ��������: �����ʼ� �޸�ʱ�䣺Jun 24, 2015 10:55:36 AM ���� ����˵�� �������� void
     * ��������
     * 
     * @throws
     */
    public static void receiver() {
	try {
	    // 1. ����������Ϣ, ����һ�� Session
	    Properties props = new Properties();
	    props.put("mail.transport.protocol", "pop3");// POP3 ����Э��
	    props.put("mail.pop.port", "995");
	    // props.put("mail.debug", "true");// ����

	    Session session = Session.getInstance(props);

	    // 2. ��ȡ Store �����ӵ�������
	    Store store = session.getStore("pop3");
	    store.connect("pop.exmail.qq.com", "ckms@utry.cn", "33Try11q");
	    // 3. ͨ�� Store ��Ĭ��Ŀ¼ Folder
	    Folder folder = store.getDefaultFolder();// Ĭ�ϸ�Ŀ¼
	    if (folder == null) {

		System.out.println("������������");
		return;
		// System.exit(1);
	    }

	    Folder popFolder = folder.getFolder("INBOX");// ��ȡ�ռ���
	    popFolder.open(Folder.READ_WRITE);// �ɶ��ʼ�,����ɾ�ʼ���ģʽ��Ŀ¼
	    // 4. �г����ռ��� �������ʼ�
	    Message[] messages = popFolder.getMessages();
	    // ȡ�����ʼ���
	    int msgCount = popFolder.getMessageCount();
	    System.out.println("�����ʼ�: " + msgCount + "��");

	    // FetchProfile fProfile = new FetchProfile();// ѡ���ʼ�������ģʽ,
	    // ��������ѡ��ͬ��ģʽ
	    // fProfile.add(FetchProfile.Item.ENVELOPE);
	    // folder.fetch(messages, fProfile);// ѡ���Ե������ʼ�

	    // 5. ѭ������ÿ���ʼ���ʵ���ʼ�תΪ���ŵĹ���
	    for (int i = 0; i < msgCount; i++) {
		Message msg = messages[i];// �����ʼ�
		// ��������Ϣ
		Address[] froms = msg.getFrom();
		if (froms != null) {
		    System.out.println("��������Ϣ:" + froms[0]);
		    InternetAddress addr = (InternetAddress) froms[0];
		    System.out.println("�����˵�ַ:" + addr.getAddress());
		    System.out.println("��������ʾ��:" + addr.getPersonal());
		}
		System.out.println("�ʼ�����:" + msg.getSubject());

		// getContent() �ǻ�ȡ��������, Part �൱�����װ
		Object o = msg.getContent();
		System.out.println(o.getClass());
		System.out.println(o);
		Multipart multipart = null;
		try {
		    multipart = (Multipart) o;
		} catch (RuntimeException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		if (null == multipart) {
		    System.out.println("is null");
		    continue;
		}
		// MultiPart
		// ���������ʼ�����(����+����)
		System.out.println("�ʼ�����" + multipart.getCount() + "�������");

		// ���δ����������
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
		    System.out.println("�����" + j + "����");
		    Part part = multipart.getBodyPart(j);// ���, ȡ��
							 // MultiPart�ĸ�������,
							 // ÿ���ֿ������ʼ�����,
		    // Ҳ��������һ��С����(MultipPart)

		    // �жϴ˰��������ǲ���һ��С����, һ����һ������ ���� Content-Type:
		    // multipart/alternative
		    if (part.getContent() instanceof Multipart) {
			Multipart p = (Multipart) part.getContent();// ת��С����
			System.out.println("С��������" + p.getCount() + "����");
			// �г�С��������������
			for (int k = 0; k < p.getCount(); k++) {
			    System.out.println("С��������:" + p.getBodyPart(k).getContent());
			    System.out.println("��������:" + p.getBodyPart(k).getContentType());
			    if (p.getBodyPart(k).getContentType().startsWith("text/plain")) {
				// �����ı�����
			    } else {
				// ���� HTML ����
			    }
			}
		    }

		    // Content-Disposition: attachment;
		    // filename="String2Java.jpg"
		    String disposition = part.getDisposition();// �����Ƿ�Ϊ������Ϣ
		    if (disposition != null) {

			System.out.println("���ָ���: " + part.getFileName());
			System.out.println("��������: " + part.getContentType());
			System.out.println("��������:" + part.getContent());
			java.io.InputStream in = part.getInputStream();// �򿪸�����������
			// ��ȡ�����ֽڲ��洢���ļ���
			java.io.FileOutputStream out = new FileOutputStream(part.getFileName());
			int data;
			while ((data = in.read()) != -1) {
			    out.write(data);
			}
			in.close();
			out.close();
		    }
		}
		// }
		// TODO newsDAO.save(news); // ���ʼ���Я������Ϣ��Ϊ���Ŵ洢����

		// 6. ɾ�������ʼ�, ���һ���ʼ���Ҫɾ��, ��������ִ��ɾ������
		// msg.setFlag(Flags.Flag.DELETED, true);
	    }
	    System.exit(0);
	    // 7. �ر� Folder ������ɾ���ʼ�, false ��ɾ��
	    popFolder.close(true);
	    // 8. �ر� store, �Ͽ���������
	    store.close();
	} catch (NoSuchProviderException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	receiver();

    }

}
