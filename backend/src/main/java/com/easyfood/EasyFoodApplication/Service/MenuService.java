package com.easyfood.EasyFoodApplication.Service;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Models.ProductWeight;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    final static double proteins = 4.0;
    final static double carbohydrates = 4.0;
    final static double fat = 9.0;
    final static double aHundredGrams = 100.0;


    @Autowired
    ProductRepository productRepository;

    private ArrayList<Product> menu;

    public MenuService() {
        this.menu = new ArrayList<Product>();
    }

    public void addInMenu(String name) {
        Product product = productRepository.findByName(name);
        menu.add(product);
    }

    public void addInMenu(ProductWeight productWeight) {
        String name = productWeight.getNameProduct();
        double weight = productWeight.getWeightProduct();
        Product product = productRepository.findByName(name);
        Product newProduct = productRepository.findByName(name);
        newProduct.setCalories(calculateCaloriesProduct(product, weight));
        newProduct.setProteins(calculateProteinGrams(product, weight));
        newProduct.setFat(calculateFatGrams(product, weight));
        newProduct.setCarbohydrates(calculateCarboGrams(product, weight));
        newProduct.setPrice(calculatePriceProductByWeight(product, weight));
        newProduct.setWeight(weight);
        menu.add(product);
    }

    public ArrayList<Product> viewProductsFromMenu() {
        return this.menu;
    }

    private double calculateProteinGrams(Product product, double weight) {
        double proteinsPer100g = product.getProteins() / aHundredGrams;
        return proteinsPer100g * weight;
    }

    private double calculateCarboGrams(Product product, double weight) {
        double carbohydratesPer100g = product.getCarbohydrates() / aHundredGrams;
        return carbohydratesPer100g * weight;
    }

    private double calculateFatGrams(Product product, double weight) {
        double fatPer100g = product.getFat() / aHundredGrams;
        return fatPer100g * weight;
    }

    private double calculateCaloriesFromProteins(double protein) {
        return protein * this.proteins;
    }

    private double calculateCaloriesFromCarbo(double carbohydrates) {
        return carbohydrates * this.carbohydrates;
    }

    private double calculateCaloriesFromFat(double fat) {
        return fat * this.fat;
    }

    private double calculateCaloriesProduct(Product product, double weight) {
        double proteinsKal = calculateCaloriesFromProteins(calculateProteinGrams(product, weight));
        double carboKal = calculateCaloriesFromCarbo(calculateCarboGrams(product, weight));
        double fatKal = calculateCaloriesFromFat(calculateFatGrams(product, weight));
        return proteinsKal + carboKal + fatKal;
    }


    private double calculatePriceProductByWeight(Product product, double weight) {
        double price = (weight * product.getPrice()) / product.getWeight();
        return price;
    }


}
