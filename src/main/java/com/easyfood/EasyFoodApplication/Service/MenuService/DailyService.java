package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Exception.DailyFoodNotFoundException;
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
import java.util.Optional;

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

    public void addMenuWithWeight(MenuWeight menuWeight) {
        dailyRepository.save(buildWeight(menuWeight));

    }

    public ArrayList<DailyFood> viewAllbreakfast(String typeOfMenu) {
        return dailyRepository.findByUserAndTypeOfMenu(getUsername(), typeOfMenu);
    }

    public void editWeight(int id, double weight) throws DailyFoodNotFoundException {
        DailyFood dailyFood = dailyRepository.findById(id)
                .orElseThrow(() -> new DailyFoodNotFoundException(id));

        // dailyRepository.save();

    }


    public void deleteProduct(int id) {
        ArrayList<DailyFood> dailyFoodList = dailyRepository.findDailyFoodsByName(getUsername());
        if (dailyFoodList.stream().anyMatch(object ->
                id == object.getId())) {
            dailyRepository.deleteById(id);
        }
    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    private DailyFood build(MenuAdd menuAdd) {
        Product product = productRepository.findByName(menuAdd.getNameProduct());
        DailyFood dailyFood = setNewParameters(product, menuAdd.getTypeOfMenu());
        dailyFood.setWeight(100.0);
        return dailyFood;
    }

    private DailyFood buildWeight(MenuWeight menuAdd) {
        Product newProduct = setCalculateParam(menuAdd.getNameProduct(), menuAdd.getWeightProduct());
        DailyFood dailyFood = setNewParameters(setCalculateParam(menuAdd.getNameProduct(), menuAdd.getWeightProduct()), menuAdd.getTypeOfMenu());
        dailyFood.setWeight(newProduct.getWeight());
        return dailyFood;
    }

    public DailyFood setNewParameters(Product product, String typeOfMenu) {
        DailyFood dailyFood = new DailyFood();
        dailyFood.setUser(getUsername());
        dailyFood.setDate(getData());
        dailyFood.setTypeOfMenu(typeOfMenu);
        dailyFood.setCalories(product.getCalories());
        dailyFood.setProteins(product.getProteins());
        dailyFood.setCarbohydrates(product.getCarbohydrates());
        dailyFood.setFat(product.getFat());
        dailyFood.setPrice(product.getPrice());
        dailyFood.setWeight(product.getWeight());
        dailyFood.setName(product.getName());
        return dailyFood;
    }

    private String getData() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    private Product setCalculateParam(String nameProduct, double weight) {
        Product foundProduct = productRepository.findByName(nameProduct);

        Product newProduct = new Product();
        newProduct.setCalories(calculateCaloriesProduct(foundProduct.getProteins(), foundProduct.getCarbohydrates(), foundProduct.getFat(), weight));
        newProduct.setProteins(calculateProteinGrams(foundProduct.getProteins(), weight));
        newProduct.setFat(calculateFatGrams(foundProduct.getFat(), weight));
        newProduct.setCarbohydrates(calculateCarboGrams(foundProduct.getCarbohydrates(), weight));
        newProduct.setPrice(calculatePriceProductByWeight(foundProduct.getPrice(), foundProduct.getWeight(), weight));
        newProduct.setWeight(weight);
        newProduct.setName(foundProduct.getName());
        return newProduct;

    }


    private double calculateProteinGrams(double proteins, double weight) {
        double proteinsPer100g = proteins / aHundredGrams;
        return proteinsPer100g * weight;
    }

    private double calculateCarboGrams(double carbohydrates, double weight) {
        double carbohydratesPer100g = carbohydrates / aHundredGrams;
        return carbohydratesPer100g * weight;
    }

    private double calculateFatGrams(double fat, double weight) {
        double fatPer100g = fat / aHundredGrams;
        return fatPer100g * weight;
    }

    private double calculateCaloriesFromProteins(double protein) {
        return (double) protein * this.proteins;
    }

    private double calculateCaloriesFromCarbo(double carbohydrates) {
        return (double) carbohydrates * this.carbohydrates;
    }

    private double calculateCaloriesFromFat(double fat) {
        return (double) fat * this.fat;
    }

    private double calculateCaloriesProduct(double proteins, double carbohydrates, double fat, double weight) {
        double proteinsKal = calculateCaloriesFromProteins(calculateProteinGrams(proteins, weight));
        double carboKal = calculateCaloriesFromCarbo(calculateCarboGrams(carbohydrates, weight));
        double fatKal = calculateCaloriesFromFat(calculateFatGrams(fat, weight));
        return (double) (proteinsKal + carboKal + fatKal);
    }

    private double calculatePriceProductByWeight(double price, double weight, double productWeight) {
       // double result = (weight * price) / productWeight;
        double result = price / productWeight;
        return result * weight;
    }


}


