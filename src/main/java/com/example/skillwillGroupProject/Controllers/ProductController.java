package com.example.skillwillGroupProject.Controllers;

import com.example.skillwillGroupProject.Enums.UserRoles;
import com.example.skillwillGroupProject.Model.Products;
import com.example.skillwillGroupProject.Model.Request.ProductRequest;
import com.example.skillwillGroupProject.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    ProductService service;


    @PostMapping("/AddProduct")
    public Products AddProduct(@RequestBody Products products, @RequestParam int userId, @RequestParam UserRoles roles)
    {
          service.AddProduct(products,userId,roles);
          return products;
    }


    @GetMapping("/getAllProducts")
    public Collection<Products> GetAllProducts(@RequestParam UserRoles roles, @RequestParam int userId)
    {
        return service.getAllProducts(userId,roles);
    }

    @PutMapping("/EditProduct")
    public Products EditProduct(@RequestParam int productId,@RequestBody ProductRequest request,@RequestParam int userId,@RequestParam UserRoles roles)
    {
        return service.EditProduct(productId,request,userId,roles);
    }

    @GetMapping("/GetProductHistory")
    public List<Products> getProductHistory(@RequestParam int userId, @RequestParam UserRoles roles)
    {
        return service.getProductHistory(userId,roles);
    }

    @DeleteMapping("/RemoveProduct")
    public void removeProduct(@RequestParam int productId,@RequestParam int userId,@RequestParam UserRoles roles) {
        service.RemoveProduct(productId,userId,roles);
    }

}
