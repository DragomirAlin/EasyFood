package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.MenuWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class LunchService extends MenuService {

    private ArrayList<Product> lunchMenu;

    public LunchService() {
        lunchMenu = new ArrayList<>();
    }

    public void addInMenu(String nameProduct){
        Product product = productRepository.findByName(nameProduct);
        lunchMenu.add(product);
    }

    public void addInMenu(MenuWeight menuWeight) {
        Product product = super.setCalculateParam(menuWeight);
        lunchMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.lunchMenu;
    }
}
