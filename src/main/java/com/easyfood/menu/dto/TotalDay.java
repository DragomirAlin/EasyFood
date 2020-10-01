package com.easyfood.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TotalDay {

    private double totalCalories;
    private double totalProteins;
    private double totalCarbohydrates;
    private double totalFats;
    private double totalPrice;
}
