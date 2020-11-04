package com.easyfood.mail.service;

import com.easyfood.mail.dto.Mail;
import com.easyfood.mail.service.MailService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Value("${easyfood.app.emailFrom}")
    private String emailFrom;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            Jinjava jinjava = new Jinjava();
            Map<String, Object> context = new HashMap<>();
            context.put("name", "Jared");
            context.put("country1", "USA");
            context.put("country2", "UKR");
            context.put("link", "www.google.ro");
            context.put("avgTone", 2.3);
            // ...
//            String template = "<html><body>" +
//                    "\n Hello, {{ name }}" +
//                    "\n </body></html>";

            String template = Resources.toString(Resources.getResource("emailTemplate.html"), Charsets.UTF_8);

            String renderedTemplate = jinjava.render(template, context);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(emailFrom);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(renderedTemplate,true);
//            mimeMessageHelper.setText("test","`<html><body><h1 style =\"color:blue;\">My first Header<h1></body></html>`");

            mailSender.send(mimeMessageHelper.getMimeMessage());


        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }
}
