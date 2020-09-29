package com.easyfood.Exception;

public class DailyFoodNotFoundException extends Exception{
        private long id;

        public DailyFoodNotFoundException(long id){
            super(String.format("Menu is not found with id : '%s'", id));
        }

    }

