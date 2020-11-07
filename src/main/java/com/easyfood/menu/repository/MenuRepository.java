package com.easyfood.menu.repository;

import com.easyfood.menu.persistence.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
//    @Query("select * from dailymenu WHERE ")
    ArrayList<Menu> findByUserAndTypeOfMenu(String name, String typeOfMenu);
    ArrayList<Menu> findAllByName(String name);
    ArrayList<Menu> findAllByNameAndDate(String name, String date);
    ArrayList<Menu> findAllByUserAndDate(String name, String date);
    void deleteById(long id);
    ArrayList<Menu> findAllByUserAndDateAndTypeOfMenu(String name, String date, String typeOfMenu);

}
