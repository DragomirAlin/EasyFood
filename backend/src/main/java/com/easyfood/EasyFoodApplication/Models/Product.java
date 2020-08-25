package com.easyfood.EasyFoodApplication.Models;


import javax.persistence.*;


@Entity
public class Product{


    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private double weight;
    @Column(name = "calories")
    private Integer calories;
    @Column(name = "proteins")
    private Integer proteins;
    @Column(name = "carbohydrates")
    private Integer carbohydrates;
    @Column(name = "fat")
    private Integer fat;
    @Column(name = "shop")
    private String shop;
    @Column(name = "price")
    private double price;
    @Column(name = "byAdded")
    private String byAdded;
git 
    public Product() {

    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getFat() {
        return fat;
    }

    public String getShop() {
        return shop;
    }

    public double getPrice() {
        return price;
    }



    public void setNameProduct(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setShop(String shop) {
        this.shop = shop;
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