package com.easyfood.EasyFoodApplication.Controllers;

import com.easyfood.EasyFoodApplication.Models.*;
import com.easyfood.EasyFoodApplication.Repository.DailyRepository;
import com.easyfood.EasyFoodApplication.Security.service.IAuthenticationFacade;
import com.easyfood.EasyFoodApplication.Security.service.UserDetailsServiceImpl;
import com.easyfood.EasyFoodApplication.Service.MenuService.DailyService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/men")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addMenu(@RequestBody MenuAdd menuAdd)  {
        dailyService.addMenu(menuAdd);
    }

    @PostMapping("/addW")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addMenu(@RequestBody MenuWeight menuWeight)  {
        dailyService.addMenuWithWeight(menuWeight);
    }



    @GetMapping("/view/{typeOfMenu}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<DailyFood> viewAll(@PathVariable String typeOfMenu){
        return dailyService.viewAllbreakfast(typeOfMenu);
    }

}
