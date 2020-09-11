package com.easyfood.EasyFoodApplication.Service.MenuService;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import com.easyfood.EasyFoodApplication.Security.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    //add new product in db
    public void addProduct(Product product){
        Product newProduct = product;
        newProduct.setBy_added(getUsername());
        productRepository.save(newProduct);
    }

    //view all products
    public List<Product> viewAllProducts(){
        List<Product> productArrayList = new ArrayList<>();
        productArrayList = productRepository.findAll();
        return productArrayList;
    }

    //search product by name
    public Product searchProductByName(String name){
        return productRepository.findByName(name);

    }

    //get username from current session
    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }




}
