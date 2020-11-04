package com.easyfood.profile.persistence;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name = "profile")
public class ProfileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String username;
    private int age;
    private double height;
    private double weight;
    private String gender;

    public ProfileUser() {

    }

    public ProfileUser(String username, int age, double height, double weight, String gender) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
