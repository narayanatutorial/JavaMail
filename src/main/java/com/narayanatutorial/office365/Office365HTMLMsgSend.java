package com.narayanatutorial.office365;


import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Office365HTMLMsgSend {

	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String USERNAME = "XXXXXXXXXX@office365.com";
	String PASSWORD = "XXXXXXXXXX";
	String HOSTNAME = "smtp.office365.com";
	String STARTTLS_PORT = "587";
	boolean STARTTLS = true;
	boolean AUTH = true;
	String FromAddress="XXXXXXXXXX@office365.com";

	public static void main(String args[]) throws MessagingException {
		String EmailSubject = "Subject:HTML Subject Body Test";
		String EmailBody = "<b>Text Message Body: Hello World</b>";
		String ToAddress = "XXXXXXXXXX@office365.com";
		Office365HTMLMsgSend office365HTMLMsgSend = new Office365HTMLMsgSend();
		office365HTMLMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
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
			session = Session.getInstance(properties, auth);

			// create mime message
			mimeMessage = new MimeMessage(session);
			
			//from address should exist in the domain
			mimeMessage.setFrom(new InternetAddress(FromAddress));
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