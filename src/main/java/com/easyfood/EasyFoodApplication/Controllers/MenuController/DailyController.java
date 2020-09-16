package com.easyfood.EasyFoodApplication.Controllers.MenuController;

import com.easyfood.EasyFoodApplication.Exception.DailyFoodNotFoundException;
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
@RequestMapping("/api/test/menu")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    //add product in menu with custom weight
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addMenu(@RequestBody MenuWeight menuWeight) {
        dailyService.addMenu(menuWeight);
    }

    //update weight product from menu list
    @PutMapping("/edit/weight/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void editWeight(@PathVariable("id") long id, @RequestBody DailyFood dailyFood) throws DailyFoodNotFoundException {
        dailyService.editWeight(id, dailyFood.getWeight());

    }

    //view all product by type of menu
    @GetMapping("/view/{typeOfMenu}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<DailyFood> viewAll(@PathVariable String typeOfMenu) {
        return dailyService.viewAllbreakfast(typeOfMenu);
    }

    @GetMapping("/view/currentDay")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<DailyFood> viewAllCurrentDay() {
        return dailyService.viewAllCurrentDay();
    }

    //delete product from menu by id
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable(value = "id") long id) {
        dailyService.deleteProduct(id);
    }

}
