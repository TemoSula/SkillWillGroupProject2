package com.example.skillwillGroupProject.Services;

import com.example.skillwillGroupProject.Enums.UserRoles;
import com.example.skillwillGroupProject.Model.Products;
import com.example.skillwillGroupProject.Model.Request.ProductRequest;
import com.example.skillwillGroupProject.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    UserService userService;

private final HashMap<Integer, Products> ProductList = new HashMap<>();
private final HashMap<Integer, List<Products>> ProductHistory = new HashMap<>();



public Collection<Products> getAllProducts(int userId,UserRoles roles)
{
      Users users = userService.getUserById(userId);
      if(users == null)
      {
          throw new RuntimeException("user not found");
      }
      if(!users.getRoles().equals(roles.Admin) || !roles.equals(UserRoles.Admin))
      {
          throw new RuntimeException("Users can't access on this endpoint");
      }
    Collection<Products> productList = ProductList.values();
    return productList;

}

     public Products getProductById(int productId)
     {
         Products product = ProductList.get(productId);
         return product;
     }


    public Products AddProduct(Products products,int userId, UserRoles roles)
   {
       Users users = userService.getUserById(userId);
       if(users == null)
       {
           throw new RuntimeException("User not found");
       }
       if(!users.getRoles().equals(roles.Admin) || !roles.equals(UserRoles.Admin))
       {
           throw new RuntimeException("Users can't access on this endpoint");
       }

       Products products1 = ProductList.get(products.getId());
       if(products1 != null)
       {
           throw new RuntimeException("Product with this id already exsits");
       }

       List<Products> productsList = ProductHistory.get(userId);
       if(productsList == null)
       {
           productsList = new ArrayList<>();
           ProductHistory.put(userId,productsList);
       }
       productsList.add(products);


      return ProductList.put(products.getId(),products);
   }

   public List<Products> getProductHistory(int userId, UserRoles roles)
   {
       Users users = userService.getUserById(userId);
       if(users == null)
       {
           throw new RuntimeException("User not found");
       }
       if(!users.getRoles().equals(roles.Admin) || !roles.equals(UserRoles.Admin))
       {
           throw new RuntimeException("Users can't access on this endpoint");
       }

       List<Products> productsList = ProductHistory.get(userId);
       if(productsList == null)
       {
           throw new RuntimeException("You have not products added yet");
       }
       return productsList;
   }

   public Products EditProduct(int productId, ProductRequest productRequest, int userId,UserRoles roles)
   {
       Users users = userService.getUserById(userId);
       if(users == null)
       {

           throw new RuntimeException("User not found");
       }
       if(!users.getRoles().equals(roles.Admin) || !roles.equals(UserRoles.Admin))
       {
           throw new RuntimeException("Users can't access on this endpoint");
       }

     Products products = ProductList.get(productId);
     if(products == null)
     {
         throw new RuntimeException("Product not found");
     }
     products.setProductName(productRequest.getProductName());
     products.setProductQuantity(productRequest.getProductQuantity());
     products.setProductPrice(productRequest.getProductPrice());
       return products;
   }

   public void RemoveProduct(int productId, int userId,UserRoles roles){

    Users users = userService.getUserById(userId);
    if(users == null)
    {

        throw new RuntimeException("User not found");
    }
       if(!users.getRoles().equals(roles.Admin) || !roles.equals(UserRoles.Admin))
       {
           throw new RuntimeException("Users can't access on this endpoint");
       }

      Products products = ProductList.get(productId);
      if(products == null)
      {
          throw new RuntimeException("Product is not exsits");
      }
      ProductList.remove(productId);

   }


}
