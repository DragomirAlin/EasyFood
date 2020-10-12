package com.easyfood.product.service;

import com.easyfood.product.persistence.Product;
import com.easyfood.product.repository.ProductRepository;
import com.easyfood.security.service.IAuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    //add new product in db
    public void addProduct(Product product){
        Product newProduct = product;
        newProduct.setBy_added(getUsername());
        productRepository.save(newProduct);
        log.info("The {} has been added to the database", newProduct.getName());
    }

    //view all products
    public List<Product> viewAllProducts(){
        List<Product> productArrayList = new ArrayList<>();
        productArrayList = productRepository.findAll();
        log.info("Return all products");
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
