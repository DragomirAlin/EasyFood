package com.easyfood.menu.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuWeight {

    private String typeOfMenu;
    private String nameProduct;
    private double weightProduct;

}