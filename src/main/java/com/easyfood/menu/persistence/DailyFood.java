package com.easyfood.menu.persistence;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "dailymenu")
public class DailyFood {

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

}
