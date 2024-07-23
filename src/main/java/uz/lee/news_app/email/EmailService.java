package uz.lee.news_app.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendHtmlEmail(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("Test email from Spring");
        message.setSubject("Authentication link below: ");
        String uniqueLinkId = UUID.randomUUID() + "";
        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<a style=\"color:red\" href=\"%s\">Press for activate your account</a>".formatted("http://localhost:8080/api/auth/verify/" + uniqueLinkId);
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);

        return uniqueLinkId;
    }
}
