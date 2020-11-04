package com.easyfood.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationFacade implements IAuthenticationFacade {


    @Override
    public Authentication getAuthentication() {
        log.info("Get user information.");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}