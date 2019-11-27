package com.narayanatutorial.gmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailTextMsgSendWithSSL {

	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String USERNAME = "XXXXXXX@gmail.com";
	String PASSWORD = "XXXXXX";
	String HOSTNAME = "smtp.gmail.com";
	String SSL_PORT = "465";
	boolean AUTH = true;
	String SERVERTYPE = "smtp";

	public static void main(String args[]) throws MessagingException {
		String EmailSubject = "Subject:Text Subject";
		String EmailBody = "Text Message Body: Hello World";
		String ToAddress = "XXXXXXXX@gmail.com";

		GmailTextMsgSendWithSSL gmailTextMsgSend = new GmailTextMsgSendWithSSL();
		gmailTextMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
	}

	public void sendGmail(String EmailSubject, String EmailBody, String ToAddress) {
		try {
			properties = new Properties();
			properties.put("mail.smtp.host", HOSTNAME);

			// SSL_PORT Enabled
			properties.put("mail.smtp.port", SSL_PORT);

			// AUTH Enabled
			properties.put("mail.smtp.auth", AUTH);

			// SSL Enabled
			properties.put("mail.smtp.socketFactory.port", SSL_PORT);
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			// Authenticating
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			};

			// creating session
			session = Session.getDefaultInstance(properties, auth);

			// create mimemessage
			mimeMessage = new MimeMessage(session);
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(ToAddress));
			mimeMessage.setSubject(EmailSubject);

			// setting text message body
			mimeMessage.setText(EmailBody);

			// sending mail
			Transport.send(mimeMessage);
			System.out.println("Mail Send Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
