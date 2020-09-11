package com.easyfood.EasyFoodApplication.Exception;

public class DailyFoodNotFoundException extends Exception{
        private int id;

        public DailyFoodNotFoundException(int id){
            super(String.format("Book is not found with id : '%s'", id));
        }

    }

