package com.easyfood.menu.service;

import com.easyfood.exception.DailyFoodNotFoundException;
import com.easyfood.menu.dto.MenuWeight;
import com.easyfood.menu.dto.TotalDay;
import com.easyfood.menu.persistence.DailyFood;

import java.util.ArrayList;

public interface MenuService {

    void addMenu(MenuWeight menuWeight);
    ArrayList<DailyFood> viewAllbreakfast(String typeOfMenu);
    void editWeight(long id, double weight) throws DailyFoodNotFoundException;
    void deleteProduct(long id);
    ArrayList<DailyFood> viewAllCurrentDay();
    ArrayList<DailyFood> viewAllMenuFromDay(String day);
    TotalDay viewTotal(String date);
}
