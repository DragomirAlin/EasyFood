package com.easyfood.menu.controller;

import com.easyfood.exception.DailyFoodNotFoundException;
import com.easyfood.menu.dto.MenuWeight;
import com.easyfood.menu.service.DailyService;
import com.easyfood.menu.persistence.DailyFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {

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
