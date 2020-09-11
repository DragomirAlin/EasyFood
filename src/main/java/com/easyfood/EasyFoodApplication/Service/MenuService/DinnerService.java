package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.MenuWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DinnerService extends MenuService {

    private ArrayList<Product> DinnerMenu;

    public DinnerService() {
        DinnerMenu = new ArrayList<>();
    }

    public void addInMenu(String nameProduct){
        Product product = productRepository.findByName(nameProduct);
        DinnerMenu.add(product);
    }

    public void addInMenu(MenuWeight menuWeight) {
        Product product = super.setCalculateParam(menuWeight);
        DinnerMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.DinnerMenu;
    }
}
