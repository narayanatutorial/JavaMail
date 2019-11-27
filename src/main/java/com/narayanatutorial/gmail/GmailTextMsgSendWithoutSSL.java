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

public class GmailTextMsgSendWithoutSSL {

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
		String EmailSubject = "Subject:Text Subject";
		String EmailBody = "Text Message Body: Hello World";
		String ToAddress = "XXXXXXX@gmail.com";
		GmailTextMsgSendWithoutSSL gmailTextMsgSend = new GmailTextMsgSendWithoutSSL();
		gmailTextMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress);
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
