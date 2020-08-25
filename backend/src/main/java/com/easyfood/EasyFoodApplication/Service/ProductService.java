package com.easyfood.EasyFoodApplication.Service;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> viewAllProducts(){
        List<Product> productArrayList = new ArrayList<>();
        productArrayList = productRepository.findAll();
        return productArrayList;
    }

    public List<Product> searchProductByName(String name){
        return productRepository.findAllByName(name);
    }


}
