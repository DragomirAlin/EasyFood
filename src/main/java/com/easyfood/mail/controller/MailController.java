package com.easyfood.mail.controller;

import com.easyfood.mail.service.MailServiceImpl;
import com.easyfood.mail.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/mail/")
public class MailController {

    @Autowired
    private MailServiceImpl mailService;

    @PostMapping("/sentTo/{emailTo}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void sendMail(@PathVariable String emailTo){
        Mail mail = new Mail();
        mail.setMailTo(emailTo);
        mail.setMailSubject("Mail sent from Spring Boot");
        mail.setMailContent("This is a test message from Spring Boot \n Dragomir Alin, \n Easy Food Application");
        mailService.sendMail(mail);
    }
}
