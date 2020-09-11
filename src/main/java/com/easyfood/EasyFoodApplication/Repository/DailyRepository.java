package com.easyfood.EasyFoodApplication.Repository;

import com.easyfood.EasyFoodApplication.Models.DailyFood;
import com.easyfood.EasyFoodApplication.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DailyRepository extends JpaRepository<DailyFood, Integer> {
//    @Query("select * from dailymenu WHERE ")
    ArrayList<DailyFood> findByUserAndTypeOfMenu(String name, String typeOfMenu);
    ArrayList<DailyFood> findDailyFoodsByName(String name);
}
