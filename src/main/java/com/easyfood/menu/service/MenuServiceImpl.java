package com.easyfood.menu.service;

import com.easyfood.exception.DailyFoodNotFoundException;
import com.easyfood.menu.dto.MenuWeight;
import com.easyfood.menu.dto.TotalDay;
import com.easyfood.product.persistence.Product;
import com.easyfood.menu.repository.MenuRepository;
import com.easyfood.product.repository.ProductRepository;
import com.easyfood.security.service.IAuthenticationFacade;
import com.easyfood.menu.persistence.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Slf4j
@Service
public class MenuServiceImpl implements MenuService{
    final static double proteins = 4.0;
    final static double carbohydrates = 4.0;
    final static double fat = 9.0;
    final static double aHundredGrams = 100.0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    //save product of Menu with new weight
    public void addMenu(MenuWeight menuWeight) {
        menuRepository.save(build(menuWeight));
        log.info("{} saved menu in database", menuWeight.getNameProduct());
    }

    //view all product by type of menu
    public ArrayList<Menu> viewAllbreakfast(String typeOfMenu) {
        log.info("Return all product by {}.", typeOfMenu);
        return menuRepository.findAllByUserAndDateAndTypeOfMenu(getUsername(), getData(), typeOfMenu);

    }

    //editing the weight of an existing product
    public void editWeight(long id, double weight) throws DailyFoodNotFoundException {
        Menu dailyFood = menuRepository.findById(id)
                .orElseThrow(() -> new DailyFoodNotFoundException(id));
        Product newProduct = calculateNewMacroProduct(dailyFood.getName(), weight);
        dailyFood.setCalories(newProduct.getCalories());
        dailyFood.setProteins(newProduct.getProteins());
        dailyFood.setCarbohydrates(newProduct.getCarbohydrates());
        dailyFood.setFat(newProduct.getFat());
        dailyFood.setPrice(newProduct.getPrice());
        dailyFood.setWeight(weight);
        menuRepository.save(dailyFood);
        log.info("The {} product has been edited.", dailyFood.getName());

    }

    //delete product from menu
    public void deleteProduct(long id) {
        menuRepository.deleteById(id);
    }

    public ArrayList<Menu> viewAllCurrentDay() {
        ArrayList<Menu> currentDayList = menuRepository.findAllByUserAndDate(getUsername(), getData());
        log.info("Return all product from current day");
        return currentDayList;
    }

    public ArrayList<Menu> viewAllMenuFromDay(String day){
        ArrayList<Menu> allFromADay = menuRepository.findAllByUserAndDate(getUsername(), day);
        log.info("Retun all product from {} .", day);
        return allFromADay;
    }


    //get username from current session
    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    //build DailyFood object with custom weight(calculate all parameters by new weight)
    private Menu build(MenuWeight menuAdd) {
        Product newProduct = calculateNewMacroProduct(menuAdd.getNameProduct(), menuAdd.getWeightProduct());
        Menu dailyFood = setParametersDailyFood(newProduct, menuAdd.getTypeOfMenu());
        log.info("Menu {} object has been built with custom weight", dailyFood.getName());
        return dailyFood;
    }

    //set new parameters for DailyFood object
    public Menu setParametersDailyFood(Product product, String typeOfMenu) {
        Menu dailyFood = new Menu.MenuBuilder()
                .user(getUsername())
                .date(getData())
                .typeOfMenu(typeOfMenu)
                .calories(product.getCalories())
                .proteins(product.getProteins())
                .carbohydrates(product.getCarbohydrates())
                .fat(product.getFat())
                .price(product.getPrice())
                .weight(product.getWeight())
                .name(product.getName())
                .build();

        log.info("set new Parameters for {}", dailyFood.getName());
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
    private Product calculateNewMacroProduct(String nameProduct, double weight) {
        Product foundProduct = productRepository.findByName(nameProduct);
        Product newProduct = new Product();
        newProduct.setCalories(calculateCaloriesProduct(foundProduct.getProteins(), foundProduct.getCarbohydrates(), foundProduct.getFat(), weight));
        newProduct.setProteins(calculateProteinGrams(foundProduct.getProteins(), weight));
        newProduct.setFat(calculateFatGrams(foundProduct.getFat(), weight));
        newProduct.setCarbohydrates(calculateCarboGrams(foundProduct.getCarbohydrates(), weight));
        newProduct.setPrice(calculatePriceProductByWeight(foundProduct.getPrice(), weight, foundProduct.getWeight()));
        newProduct.setWeight(weight);
        newProduct.setName(foundProduct.getName());
        log.info("calculate macronutrients for {}", newProduct.getName());
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
        double result = (price * weight) / productWeight;
        return result;
    }



    public TotalDay viewTotal(String date) {
        ArrayList<Menu> currentDayList = menuRepository.findAllByUserAndDate(getUsername(), date);
        TotalDay totalDay = new TotalDay();
        totalDay.setTotalCalories(totalCalories(currentDayList));
        totalDay.setTotalProteins(totalProteins(currentDayList));
        totalDay.setTotalCarbohydrates(totalCarbohydrates(currentDayList));
        totalDay.setTotalFats(totalFats(currentDayList));
        totalDay.setTotalPrice(totalPrice(currentDayList));
        return totalDay;
    }


    public double totalCalories(ArrayList<Menu> dailyFoods) {
        double allCalories = dailyFoods.stream().map(Menu::getCalories).reduce(0.0, Double::sum);
        return allCalories;
    }

    public double totalProteins(ArrayList<Menu> dailyFoods) {
        double allProteins = dailyFoods.stream().map(Menu::getProteins).reduce(0.0, Double::sum);
        return allProteins;
    }

    public double totalCarbohydrates(ArrayList<Menu> dailyFoods) {
        double allCarbohydrates = dailyFoods.stream().map(Menu::getCarbohydrates).reduce(0.0, Double::sum);
        return allCarbohydrates;
    }

    public double totalFats(ArrayList<Menu> dailyFoods) {
        double allFats = dailyFoods.stream().map(Menu::getFat).reduce(0.0, Double::sum);
        return allFats;
    }

    public double totalPrice(ArrayList<Menu> dailyFoods) {
        double allPrice = dailyFoods.stream().map(Menu::getPrice).reduce(0.0, Double::sum);
        return allPrice;
    }


}


