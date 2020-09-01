package com.easyfood.EasyFoodApplication.Models;


import javax.persistence.*;


@Entity
public class Product {


    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private double weight;
    @Column(name = "calories")
    private double calories;
    @Column(name = "proteins")
    private double proteins;
    @Column(name = "carbohydrates")
    private double carbohydrates;
    @Column(name = "fat")
    private double fat;
    @Column(name = "shop")
    private String shop;
    @Column(name = "price")
    private double price;
    @Column(name = "byAdded")
    private String byAdded;

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getByAdded() {
        return byAdded;
    }

    public void setByAdded(String byAdded) {
        this.byAdded = byAdded;
    }
}