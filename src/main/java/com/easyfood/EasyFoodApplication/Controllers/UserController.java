package com.easyfood.EasyFoodApplication.Controllers;

import com.easyfood.EasyFoodApplication.Models.User;
import com.easyfood.EasyFoodApplication.Security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user")
public class UserController {


    }

