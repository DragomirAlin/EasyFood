package com.easyfood.EasyFoodApplication.Controllers.MenuController;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.MenuWeight;
import com.easyfood.EasyFoodApplication.Service.MenuService.BreakfastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/menu/breakfast")
public class BreakfastController {

    @Autowired
    BreakfastService breakfastService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductInMenu(@RequestBody String nameProduct) {
        breakfastService.addInMenu(nameProduct);
    }

    @PostMapping("/addWithWeight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductWithWeight(@RequestBody MenuWeight menuWeight) {
        breakfastService.addInMenu(menuWeight);
    }

    @GetMapping(path = "/viewMenu")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<Product> viewMenu() {
        return breakfastService.viewProductsFromMenus();
    }
}
