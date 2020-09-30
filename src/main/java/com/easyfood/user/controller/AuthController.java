package com.easyfood.user.controller;

import com.easyfood.role.dto.ERole;
import com.easyfood.role.persistence.Role;
import com.easyfood.user.persistence.User;
import com.easyfood.security.payload.request.LoginRequest;
import com.easyfood.security.payload.request.SignupRequest;
import com.easyfood.security.payload.response.JwtResponse;
import com.easyfood.security.payload.response.MessageResponse;
import com.easyfood.role.repository.RoleRepository;
import com.easyfood.user.repository.UserRepository;
import com.easyfood.security.jwt.JwtUtils;
import com.easyfood.security.service.UserDetailsImpl;
import com.easyfood.user.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    //login
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    //register
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}