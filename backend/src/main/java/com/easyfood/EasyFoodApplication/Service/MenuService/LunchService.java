package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class LunchService extends MenuService {

    private ArrayList<Product> lunchMenu;

    public LunchService() {
        lunchMenu = new ArrayList<>();
    }

    public void addMenu(ProductWeight productWeight) {
        Product product = super.setCalculateParam(productWeight);
        lunchMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.lunchMenu;
    }
}
