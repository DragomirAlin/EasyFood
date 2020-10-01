package com.easyfood.user.controller;

import com.easyfood.user.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;


    @PostMapping("/forgot/{userEmail}")
    public void processForgotPasswordRequest(@PathVariable String userEmail, HttpServletRequest request) {
        passwordService.processForgotPasswordRequest(userEmail, request);
    }

    @GetMapping("/reset")
    public ResponseEntity processForgotPasswordRequest(@RequestParam("token") String token, HttpServletResponse res) {
        return passwordService.processForgotPasswordRequest(token, res);
    }

    @PostMapping("/reset")
    public void setNewPassword(@RequestParam Map<String, String> requestParams) {
        passwordService.setNewPassword(requestParams);
    }


}
