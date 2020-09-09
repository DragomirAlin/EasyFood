package com.easyfood.EasyFoodApplication.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dailymenu")
public class DailyFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 20)
    private String date;


    @JoinColumn(name = "user_name")
    private String user;

    @Column(name = "type_menu8")
    private String typeOfMenu;

    @Column(name = "nameProduct")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTypeOfMenu() {
        return typeOfMenu;
    }

    public void setTypeOfMenu(String typeOfMenu) {
        this.typeOfMenu = typeOfMenu;
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
}
