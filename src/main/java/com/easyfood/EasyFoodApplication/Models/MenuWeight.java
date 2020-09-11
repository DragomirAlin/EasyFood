package com.easyfood.EasyFoodApplication.Models;

public class MenuWeight {

    private String typeOfMenu;
    private String nameProduct;
    private double weightProduct;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(double weightProduct) {
        this.weightProduct = weightProduct;
    }

    public String getTypeOfMenu() {
        return typeOfMenu;
    }

    public void setTypeOfMenu(String typeOfMenu) {
        this.typeOfMenu = typeOfMenu;
    }
}
