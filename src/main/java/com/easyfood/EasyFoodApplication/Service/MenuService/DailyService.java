package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.*;
import com.easyfood.EasyFoodApplication.Repository.DailyRepository;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import com.easyfood.EasyFoodApplication.Security.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class DailyService {

    final static double proteins = 4.0;
    final static double carbohydrates = 4.0;
    final static double fat = 9.0;
    final static double aHundredGrams = 100.0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    public void addMenu(MenuAdd menuAdd) {
        dailyRepository.save(build(menuAdd));
    }

    public void addMenuWithWeight(MenuWeight menuWeight){
        dailyRepository.save(buildWeight(menuWeight));

    }

    public ArrayList<DailyFood> viewAllbreakfast(String typeOfMenu) {
        return dailyRepository.findByUserAndTypeOfMenu(getUsername(), typeOfMenu);
    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    private DailyFood build(MenuAdd menuAdd) {
        Product product = productRepository.findByName(menuAdd.getNameProduct());
        DailyFood dailyFood = new DailyFood();
        dailyFood.setUser(getUsername());
        dailyFood.setDate(getData());
        dailyFood.setTypeOfMenu(menuAdd.getTypeOfMenu());
        dailyFood.setCalories(product.getCalories());
        return dailyFood;
    }

    private DailyFood buildWeight(MenuWeight menuAdd) {
        Product newProduct = setCalculateParam(menuAdd.getNameProduct(), menuAdd.getWeightProduct());
        DailyFood dailyFood = new DailyFood();
        dailyFood.setUser(getUsername());
        dailyFood.setDate(getData());
        dailyFood.setTypeOfMenu(menuAdd.getTypeOfMenu());
        dailyFood.setCalories(newProduct.getCalories());
        return dailyFood;
    }

    private String getData() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    private Product setCalculateParam(String name, double weight) {
        Product product = productRepository.findByName(name);
        Product newProduct = productRepository.findByName(name);
        newProduct.setCalories(calculateCaloriesProduct(product, weight));
        newProduct.setProteins(calculateProteinGrams(product, weight));
        newProduct.setFat(calculateFatGrams(product, weight));
        newProduct.setCarbohydrates(calculateCarboGrams(product, weight));
        newProduct.setPrice(calculatePriceProductByWeight(product, weight));
        newProduct.setWeight(weight);
        return newProduct;

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


