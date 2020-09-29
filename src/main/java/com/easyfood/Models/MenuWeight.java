package com.easyfood.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
public class MenuWeight {

    private String typeOfMenu;
    private String nameProduct;
    private double weightProduct;

}