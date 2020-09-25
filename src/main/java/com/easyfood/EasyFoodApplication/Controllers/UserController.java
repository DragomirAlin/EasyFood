package com.easyfood.EasyFoodApplication.Controllers;

import com.easyfood.EasyFoodApplication.Models.User;
import com.easyfood.EasyFoodApplication.Security.service.UserDetailsServiceImpl;
import com.easyfood.EasyFoodApplication.Service.MenuService.ProductService;
import com.easyfood.EasyFoodApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user/")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/viewUser/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Optional<User> getUser(@PathVariable String email){
        return userService.loadUserFromEmail(email);
    }





}


