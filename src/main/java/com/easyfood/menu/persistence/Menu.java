package com.easyfood.menu.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "dailymenu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 20)
    private String date;

    @Column(name = "user")
    private String user;

    @Column(name = "type_menu")
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

    public Menu(MenuBuilder builder) {
        this.date = builder.date;
        this.user = builder.user;
        this.typeOfMenu = builder.typeOfMenu;
        this.name = builder.name;
        this.weight = builder.weight;
        this.calories = builder.calories;
        this.proteins = builder.proteins;
        this.carbohydrates = builder.carbohydrates;
        this.fat = builder.fat;
        this.shop = builder.shop;
        this.price = builder.price;
    }




    public static class MenuBuilder {

        private String date;
        private String user;
        private String typeOfMenu;
        private String name;
        private double weight;
        private double calories;
        private double proteins;
        private double carbohydrates;
        private double fat;
        private String shop;
        private double price;

        public MenuBuilder( @NotBlank @Size(max = 20) String date, String user, String typeOfMenu, String name, double weight, double calories, double proteins, double carbohydrates, double fat, String shop, double price) {
            this.date = date;
            this.user = user;
            this.typeOfMenu = typeOfMenu;
            this.name = name;
            this.weight = weight;
            this.calories = calories;
            this.proteins = proteins;
            this.carbohydrates = carbohydrates;
            this.fat = fat;
            this.shop = shop;
            this.price = price;
        }

        public MenuBuilder(){

        }

        public MenuBuilder date(String date) {
            this.date = date;
            return this;
        }

        public MenuBuilder user(String user) {
            this.user = user;
            return this;
        }

        public MenuBuilder typeOfMenu(String typeOfMenu) {
            this.typeOfMenu = typeOfMenu;
            return this;
        }

        public MenuBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MenuBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public MenuBuilder calories(double calories) {
            this.calories = calories;
            return this;
        }

        public MenuBuilder proteins(double proteins) {
            this.proteins = proteins;
            return this;
        }

        public MenuBuilder carbohydrates(double carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public MenuBuilder fat(double fat) {
            this.fat = fat;
            return this;
        }

        public MenuBuilder shop(String shop) {
            this.shop = shop;
            return this;
        }

        public MenuBuilder price(double price) {
            this.price = price;
            return this;
        }

        public Menu build(){
            Menu menu = new Menu(this);
            return menu;
        }

    }
}
