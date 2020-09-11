package com.easyfood.EasyFoodApplication.Controllers.MenuController;

import com.easyfood.EasyFoodApplication.Models.Product;
import com.easyfood.EasyFoodApplication.Security.service.UserDetailsImpl;
import com.easyfood.EasyFoodApplication.Service.MenuService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping("/viewAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Product> viewAllProduct(){
        return productService.viewAllProducts();
    }

    @GetMapping("/searchByProductName/{nameProduct}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Product searchByName(@PathVariable String nameProduct){
        return productService.searchProductByName(nameProduct);
    }


}
