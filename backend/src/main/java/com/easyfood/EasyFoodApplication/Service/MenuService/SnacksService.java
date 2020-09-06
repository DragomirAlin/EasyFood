package com.easyfood.EasyFoodApplication.Service.MenuService;


import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SnacksService extends MenuService {

    private ArrayList<Product> SnacksMenu;

    public SnacksService() {
        SnacksMenu = new ArrayList<>();
    }

    public void addMenu(ProductWeight productWeight) {
        Product product = super.setCalculateParam(productWeight);
        SnacksMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.SnacksMenu;
    }
}
