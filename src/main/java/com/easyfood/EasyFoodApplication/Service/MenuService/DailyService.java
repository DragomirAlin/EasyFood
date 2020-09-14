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

    //save product of Menu in db
    public void addMenu(MenuAdd menuAdd) {
        dailyRepository.save(build(menuAdd));
    }

    //save product of Menu with new weight
    public void addMenuWithWeight(MenuWeight menuWeight) {
        dailyRepository.save(buildWeight(menuWeight));

    }

    //view all product by type of menu
    public ArrayList<DailyFood> viewAllbreakfast(String typeOfMenu) {
        return dailyRepository.findByUserAndTypeOfMenu(getUsername(), typeOfMenu);
    }

    //editing the weight of an existing product
    public void editWeight(int id, double weight) throws DailyFoodNotFoundException {
        DailyFood dailyFood = dailyRepository.findById(id)
                .orElseThrow(() -> new DailyFoodNotFoundException(id));

        // dailyRepository.save();

    }

    //delete product from menu
    public void deleteProduct(int id) {
//        ArrayList<DailyFood> dailyFoodList = dailyRepository.findAllByName(getUsername());
//        if (dailyFoodList.stream().anyMatch(object ->
//                id == object.getId())) {
//            dailyRepository.deleteById(id);
//        }

        dailyRepository.deleteById(id);

    }

    public ArrayList<DailyFood> viewAllCurrentDay(){
        ArrayList<DailyFood> currentDayList = dailyRepository.findAllByUserAndDate(getUsername(),getData());
        return currentDayList;
    }


    //get username from current session
    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    //build DailyFood object with standard weight(100grams)
    private DailyFood build(MenuAdd menuAdd) {
        Product product = productRepository.findByName(menuAdd.getNameProduct());
        DailyFood dailyFood = setNewParameters(product, menuAdd.getTypeOfMenu());
        dailyFood.setWeight(100.0);
        return dailyFood;
    }

    //build DailyFood object with custom weight(calculate all parameters by new weight)
    private DailyFood buildWeight(MenuWeight menuAdd) {
        Product newProduct = setCalculateParam(menuAdd.getNameProduct(), menuAdd.getWeightProduct());
        DailyFood dailyFood = setNewParameters(setCalculateParam(menuAdd.getNameProduct(), menuAdd.getWeightProduct()), menuAdd.getTypeOfMenu());
        dailyFood.setWeight(newProduct.getWeight());
        return dailyFood;
    }

    //set new parameters for DailyFood object
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

    //get current datetime with custom format
    private String getData() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    //calculated parameters by weight
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

    //calculated proteins per 100grams and return protein grams for new weight
    private double calculateProteinGrams(double proteins, double weight) {
        double proteinsPer100g = proteins / aHundredGrams;
        return proteinsPer100g * weight;
    }

    //calculated carbohydrates per 100grams and return carbo grams for new weight
    private double calculateCarboGrams(double carbohydrates, double weight) {
        double carbohydratesPer100g = carbohydrates / aHundredGrams;
        return carbohydratesPer100g * weight;
    }

    //calculated fats per 100grams and return fats grams for new weight
    private double calculateFatGrams(double fat, double weight) {
        double fatPer100g = fat / aHundredGrams;
        return fatPer100g * weight;
    }

    //calculated calories from grams of proteins
    private double calculateCaloriesFromProteins(double protein) {
        return (double) protein * this.proteins;
    }

    //calculated calories from grams of carbos
    private double calculateCaloriesFromCarbo(double carbohydrates) {
        return (double) carbohydrates * this.carbohydrates;
    }

    //calculated calories from grams of fats
    private double calculateCaloriesFromFat(double fat) {
        return (double) fat * this.fat;
    }

    //calculated total calories
    private double calculateCaloriesProduct(double proteins, double carbohydrates, double fat, double weight) {
        double proteinsKal = calculateCaloriesFromProteins(calculateProteinGrams(proteins, weight));
        double carboKal = calculateCaloriesFromCarbo(calculateCarboGrams(carbohydrates, weight));
        double fatKal = calculateCaloriesFromFat(calculateFatGrams(fat, weight));
        return (double) (proteinsKal + carboKal + fatKal);
    }

    //calculated price by weight
    private double calculatePriceProductByWeight(double price, double weight, double productWeight) {
        // double result = (weight * price) / productWeight;
        double result = price / productWeight;
        return result * weight;
    }


}


