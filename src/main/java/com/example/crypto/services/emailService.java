package com.example.crypto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
public class emailService {

    @Autowired
    private userService us;

   public void sendmail(String to) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("assistantcrypto3@gmail.com", "Springlove,3");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("assistantcrypto3@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("CryptoAssistant password Reset");
        msg.setContent("CryptoAssistant password Reset", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String code = getRandomNumberString();
        messageBodyPart.setContent("Your password reset code: "+code, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        //attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
        us.setCode(to,code);
    }

    public void verifymail(String to,String id) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("assistantcrypto3@gmail.com", "Springlove,3");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("assistantcrypto3@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("CryptoAssistant Email Verification");
        msg.setContent("CryptoAssistant Email Verification", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setContent("Please click the link to verify your email address "+ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        +"/verifyEmail/"+id, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        //attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    public String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
