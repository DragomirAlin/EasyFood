package com.easyfood.EasyFoodApplication.Controllers.MenuController;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import com.easyfood.EasyFoodApplication.Service.MenuService.BreakfastService;
import com.easyfood.EasyFoodApplication.Service.MenuService.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/menu/lunch")
public class LunchController {

    @Autowired
    LunchService lunchService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductInMenu(@RequestBody String nameProduct) {
        lunchService.addInMenu(nameProduct);
    }

    @PostMapping("/addWithWeight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductWithWeight(@RequestBody ProductWeight productWeight) {
        lunchService.addInMenu(productWeight);
    }

    @GetMapping(path = "/viewMenu")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<Product> viewMenu() {
        return lunchService.viewProductsFromMenu();
    }


}
