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

    public void addProduct(Product product){
        Product newProduct = product;
        newProduct.setBy_added(getUsername());
        productRepository.save(newProduct);
    }

    public List<Product> viewAllProducts(){
        List<Product> productArrayList = new ArrayList<>();
        productArrayList = productRepository.findAll();
        return productArrayList;
    }

    public Product searchProductByName(String name){
        return productRepository.findByName(name);

    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }




}
