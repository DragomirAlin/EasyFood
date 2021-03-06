package com.easyfood.product.controller;

import com.easyfood.product.persistence.Product;
import com.easyfood.product.service.ProductService;
import com.easyfood.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    //add new product requestBody{name, calories, carbohydrates, proteins, fat, weight(the product when purchased), price(the product when purchased), shop
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    //view all products
    @GetMapping("/viewAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Product> viewAllProduct(){
        return productService.viewAllProducts();
    }

    //search product by name
    @GetMapping("/searchByProductName/{nameProduct}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Product searchByName(@PathVariable String nameProduct){
        return productService.searchProductByName(nameProduct);
    }


}
