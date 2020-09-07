package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BreakfastService extends MenuService {

    @Autowired
    ProductRepository productRepository;

    private ArrayList<Product> breakfastMenu = new ArrayList<>();;

    public BreakfastService() {
        this.breakfastMenu = new ArrayList<Product>();
    }

    public void addInMenu(String nameProduct){
        Product product = productRepository.findByName(nameProduct);
        breakfastMenu.add(product);
    }


    public void addInMenu(ProductWeight productWeight) {
        Product product = super.setCalculateParam(productWeight);
        breakfastMenu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenus() {
        return this.breakfastMenu;
    }

}
