package com.easyfood.Controllers;

import com.easyfood.Models.User;
import com.easyfood.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/viewUser/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Optional<User> getUser(@PathVariable String email){
        return userService.loadUserFromEmail(email);
    }





}


