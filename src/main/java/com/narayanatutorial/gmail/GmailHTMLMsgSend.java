package com.narayanatutorial.gmail;


import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailHTMLMsgSend {

	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String USERNAME = "XXXXXXX@gmail.com";
	String PASSWORD = "XXXXXXX";
	String HOSTNAME = "smtp.gmail.com";
	String STARTTLS_PORT = "587";
	boolean STARTTLS = true;
	boolean AUTH = true;

	public static void main(String args[]) throws MessagingException {
		String EmailSubject = "Subject:HTML Subject Body Test";
		String EmailBody = "<b>Text Message Body: Hello World</b>";
		String ToAddress = "XXXXXXX@gmail.com";
		
		GmailHTMLMsgSend gmailHTMLMsgSend = new GmailHTMLMsgSend();
		gmailHTMLMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
	}

	public void sendGmail(String EmailSubject, String EmailBody, String ToAddress) {
		try {
			properties = new Properties();
			properties.put("mail.smtp.host", HOSTNAME);
			// Setting STARTTLS_PORT
			properties.put("mail.smtp.port", STARTTLS_PORT);
			// AUTH enabled
			properties.put("mail.smtp.auth", AUTH);
			// STARTTLS enabled
			properties.put("mail.smtp.starttls.enable", STARTTLS);

			// Authenticating
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			};

			// creating session
			session = Session.getDefaultInstance(properties, auth);
			System.out.println("Session created successfully");
			// create mimemessage
			mimeMessage = new MimeMessage(session);
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(ToAddress));
			mimeMessage.setSubject(EmailSubject);

			// setting HTML message body
			mimeMessage.setContent(EmailBody,"text/html; charset=utf-8");

			// sending mail
			Transport.send(mimeMessage);
			System.out.println("Mail Send Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
