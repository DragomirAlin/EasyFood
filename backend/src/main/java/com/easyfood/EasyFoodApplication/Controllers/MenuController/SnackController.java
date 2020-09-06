package com.easyfood.EasyFoodApplication.Controllers.MenuController;


import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import com.easyfood.EasyFoodApplication.Service.MenuService.DinnerService;
import com.easyfood.EasyFoodApplication.Service.MenuService.SnacksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/menu/snack")
public class SnackController {

    @Autowired
    SnacksService snacksService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductInMenu(@RequestBody String nameProduct) {
        snacksService.addInMenu(nameProduct);
    }

    @PostMapping("/addWithWeight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProductWithWeight(@RequestBody ProductWeight productWeight) {
        snacksService.addInMenu(productWeight);
    }

    @GetMapping(path = "/viewMenu")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ArrayList<Product> viewMenu() {
        return snacksService.viewProductsFromMenu();
    }
}