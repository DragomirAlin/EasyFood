package com.easyfood.EasyFoodApplication.Service.MenuService;


import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SnacksService extends MenuService {

    private ArrayList<Product> snacksMenu;

    public SnacksService() {
        snacksMenu = new ArrayList<>();
    }

    public void addMenu(String nameProduct){
        Product product = productRepository.findByName(nameProduct);
        snacksMenu.add(product);
    }

    public void addMenu(ProductWeight productWeight) {
        Product product = super.setCalculateParam(productWeight);
        snacksMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.snacksMenu;
    }
}
