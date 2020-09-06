package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BreakfastService extends MenuService {

    private ArrayList<Product> breakfastMenu;

    public BreakfastService() {
        breakfastMenu = new ArrayList<>();
    }

    public void addMenu(ProductWeight productWeight) {
        Product product = super.setCalculateParam(productWeight);
        breakfastMenu.add(product);
    }

    public void addMenu(String nameProduct){
        Product product = productRepository.findByName(nameProduct);
        breakfastMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.breakfastMenu;
    }

}
