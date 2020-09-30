package com.easyfood.user.controller;

import com.easyfood.mail.dto.Mail;
import com.easyfood.mail.service.MailService;
import com.easyfood.security.payload.response.MessageResponse;
import com.easyfood.user.persistence.User;
import com.easyfood.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/password")
public class PasswordController {

    @Value("${easyfood.app.emailFrom}")
    private String sender;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/forgot/{userEmail}")
    public void processForgotPasswordRequest(@PathVariable String userEmail, HttpServletRequest request) {

        Optional<User> optional = userService.findUserByEmail(userEmail);

        if (!optional.isPresent()) {
            log.info("{} this email do not exist", userEmail);
        } else {
            User user = optional.get();
            user.setTokenReset(UUID.randomUUID().toString());
            userService.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080";

            Mail passwordResetEmail = new Mail();
            passwordResetEmail.setMailFrom(sender);
            passwordResetEmail.setMailTo(user.getEmail());
            passwordResetEmail.setMailSubject("Password Reset Request");
            passwordResetEmail.setMailContent("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.getTokenReset());

            emailService.sendMail(passwordResetEmail);
            log.info("A password reset link has been sent to {} " + userEmail);
        }
    }

    @GetMapping("/reset")
    public ResponseEntity processForgotPasswordRequest(@RequestParam("token") String token, HttpServletResponse res) {

        Optional<User> user = userService.findByTokenReset(token);

        if (user.isPresent()) {
            res.setStatus(HttpServletResponse.SC_OK);
            log.info("{} accessed the password reset page",user.get().getUsername());
            return ResponseEntity.ok(new MessageResponse("Access for reset password"));
        }

        log.warn("This token {} is wrong");
        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return (ResponseEntity) ResponseEntity.badRequest().body(new MessageResponse("Wrong token"));

    }

    @PostMapping("/reset")
    public void setNewPassword(@RequestParam Map<String, String> requestParams) {
        Optional<User> user = userService.findByTokenReset(requestParams.get("token"));

        if (user.isPresent()) {

            User resetUser = user.get();

            // Set new password
            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
            log.info("Set new password for {}", user.get().getUsername());
            // Set the reset token to null so it cannot be used again
            resetUser.setTokenReset(null);
            log.info("Token reset for {}", user.get().getUsername());

            // Save user
            userService.save(resetUser);
        }


    }




}
