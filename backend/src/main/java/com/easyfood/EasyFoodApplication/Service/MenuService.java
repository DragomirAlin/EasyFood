package com.easyfood.EasyFoodApplication.Service;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    final static double proteins = 4;
    final static double carbohydrates = 4;
    final static double fat = 9;

    @Autowired
    ProductRepository productRepository;

    private ArrayList<Product> menu;

    public MenuService(){
        this.menu = new ArrayList<Product>();
    }

    private void addInMenu(String name){
        Product product = productRepository.findByName(name);
        menu.add(product);
    }

    private void addInMenu(String name, double weight){
        Product product = productRepository.findByName(name);
         product.setWeight(weight);
        menu.add(product);
    }

    private ArrayList<Product> viewProductsFromMenu(){
        return this.menu;
    }

    private double calculateProteinGrams(Product product, double weight){
        double proteinsPer100g = product.getProteins()/100;
        return proteinsPer100g * weight;
    }

    private double calculateCarboGrams(Product product, double weight){
        double carbohydratesPer100g = product.getCarbohydrates()/100;
        return carbohydratesPer100g * weight;
    }

    private double calculateFatGrams(Product product, double weight){
        double fatPer100g = product.getFat()/100;
        return fatPer100g * weight;
    }

    private double calculateCaloriesFromProteins(double protein){
        return protein * this.proteins;
    }

    private double calculateCaloriesFromCarbo(double carbohydrates){
        return carbohydrates * this.carbohydrates;
    }

    private double calculateCaloriesFromFat(double fat){
        return fat * this.fat;
    }

    public double calculateCaloriesProduct(Product product, double weight){
       double proteinsKal = calculateCaloriesFromProteins(calculateProteinGrams(product,weight));
       double carboKal = calculateCaloriesFromCarbo(calculateCarboGrams(product, weight));
       double fatKal = calculateCaloriesFromFat(calculateFatGrams(product,weight));
       return proteinsKal + carboKal + fatKal;
    }





}
