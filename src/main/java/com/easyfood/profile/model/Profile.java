package com.easyfood.profile.model;


import lombok.Data;

@Data
public class Profile {
     private String username;
     private int age;
     private double height;
     private double weight;
     private String gender;

     public Profile(){

     }

     public Profile(String username, int age, double height, double weight, String gender) {
          this.username = username;
          this.age = age;
          this.height = height;
          this.weight = weight;
          this.gender = gender;
     }
}
