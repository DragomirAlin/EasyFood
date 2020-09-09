package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.*;
import com.easyfood.EasyFoodApplication.Repository.DailyRepository;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import com.easyfood.EasyFoodApplication.Repository.UserRepository;
import com.easyfood.EasyFoodApplication.Security.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.aspectj.bridge.Version.getTime;

@Service
public class DailyService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;



    public void addMenu(MenuAdd menuAdd){
        dailyRepository.save(build(menuAdd));
    }

    public ArrayList<DailyFood> viewAllbreakfast(String typeOfMenu){
       return dailyRepository.findByUserAndTypeOfMenu(getUsername(),typeOfMenu);
    }

    private String getUsername(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    private DailyFood build(MenuAdd menuAdd){
        Product product = productRepository.findByName(menuAdd.getNameProduct());
        DailyFood dailyFood = new DailyFood();
        dailyFood.setUser(getUsername());
        dailyFood.setDate(getData());
        dailyFood.setTypeOfMenu(menuAdd.getTypeOfMenu());
        dailyFood.setCalories(product.getCalories());
        return dailyFood;
    }

    private String getData(){
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }



}


