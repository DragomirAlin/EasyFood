package com.easyfood.EasyFoodApplication.Exception;

public class DailyFoodNotFoundException extends Exception{
        private long id;

        public DailyFoodNotFoundException(long id){
            super(String.format("Book is not found with id : '%s'", id));
        }

    }

