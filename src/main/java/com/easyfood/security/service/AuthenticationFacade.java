package com.easyfood.security.service;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public Authentication getAuthentication() {
        logger.info("Get user information.");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}