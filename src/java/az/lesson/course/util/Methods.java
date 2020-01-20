package az.lesson.course.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Methods {

    public static boolean sendMail(String subject, String text, String mailTo) {
        boolean result = false;
        final String username = "stharpin@gmail.com";
        final String password = "natiq555orator";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("stharpin@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Mail gonderildi");
            result = true;
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
