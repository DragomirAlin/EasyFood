package com.easyfood.Models;

import javax.persistence.*;

@Entity
@Table(name = "menus")
public class Prod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EMenu name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EMenu getName() {
        return name;
    }

    public void setName(EMenu name) {
        this.name = name;
    }
}
