package com.example.skillwillGroupProject.Services;

import com.example.skillwillGroupProject.Enums.UserRoles;
import com.example.skillwillGroupProject.Model.Products;
import com.example.skillwillGroupProject.Model.UserOrder;
import com.example.skillwillGroupProject.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserOrderService {

    @Autowired
    UserService userService;


    @Autowired
    ProductService productService;



    private final HashMap<Integer, List<Products>> Orders = new HashMap<>();

    //gaanebe tavi
    private final HashMap<Integer, List<Products>> PurchaseHistory = new HashMap<>();



    public UserOrder AddInCart(int userId, int productId, UserRoles roles)//
    {
         Users user = userService.getUserById(userId);
         if(user == null)
         {
             throw new RuntimeException("User not found");
         }

        if (!user.getRoles().equals(roles.User) || !roles.equals(roles.User)) {

            throw new RuntimeException("Admins cannot buy products");
        }


         Products products = productService.getProductById(productId);
         if(products == null)
         {
             throw new RuntimeException("Product not found");
         }
        List<Products> userOrders = Orders.get(userId);
         if(userOrders == null)
         {
             userOrders = new ArrayList<Products>();
             Orders.put(userId,userOrders);
         }


        userOrders.add(products);

         UserOrder userOrder = new UserOrder();
         userOrder.setUserId(user.getId());
         userOrder.setProductId(products.getId());
        return userOrder;


    }

    public void RemoveFromCart(int userId, int productId, UserRoles roles)
    {
          Users user = userService.getUserById(userId);
          if(user == null)
          {

              throw new RuntimeException("User not found");
          }
          if (!user.getRoles().equals(roles.User) || !roles.equals(roles.User)) {

            throw new RuntimeException("Admins cannot buy products");
        }
          Products products = productService.getProductById(productId);
          if(products == null)
          {
              throw new RuntimeException("product not found");
          }

          List<Products> prodlist = Orders.get(userId);

          if(prodlist == null)
          {
              throw new RuntimeException("User is not exsits");
          }

          Iterator<Products> iterator = prodlist.iterator();

          while(iterator.hasNext())
          {
              Products products1 = iterator.next();
              if(productId == products1.getId())
              {
                  iterator.remove();
                  break;
              }
          }
          Orders.put(userId,prodlist);

          /*for(String usersID: Orders.keySet())
          {
              if(usersID.equals(userId))
              {
                  for(List<Products> productsList: Orders.values())
                  {

                   for(Products products1 : productsList)
                   {
                       List<Products> productsArrayList = new ArrayList<>();
                       productsArrayList.add(products1);
                       if(productId.equals(products1.getId()))
                       {
                           productsArrayList.remove(products1);
                       }
                       Orders.put(userId, productsArrayList);
                   }
                  }
              }
          }*/


    }


    public String BuyProduct(int userId, int productId,UserRoles roles)
    {
        Users users = userService.getUserById(userId);
        if(users == null)
        {
            throw new RuntimeException("User not found");
        }
        if (!users.getRoles().equals(roles.User) || !roles.equals(roles.User)) {

            throw new RuntimeException("Admins cannot buy products");
        }
        Products products = productService.getProductById(productId);
        if(products == null)
        {
            throw new RuntimeException("product not found");
        }

                           List<Products> productsList = Orders.get(userId);
                              if(productsList == null)
                              {
                                  throw new RuntimeException("No orders found for this user");
                              }
                           for(Products justproducts : productsList){
                            if(justproducts.getId() == productId) {


                                if (justproducts.getProductPrice() > users.getAmount()) {
                                    throw new RuntimeException("you have not enough money");
                                }
                                if (justproducts.getProductQuantity() == 0) {
                                    throw new RuntimeException("maragshi agar aris");
                                }
                                users.setAmount(users.getAmount() - justproducts.getProductPrice());
                                justproducts.setProductQuantity(justproducts.getProductQuantity() - 1);
                                //List<Products> prodctList = new ArrayList<>();
                                List<Products> prodctList = PurchaseHistory.get(userId);
                                if (prodctList == null) {
                                    prodctList = new ArrayList<>();
                                    PurchaseHistory.put(userId, prodctList);
                                }

                                prodctList.add(products);
                                return "You purchase product succesfully";
                            }

                        }


        throw new RuntimeException("Product not found in orders");

    }


    public List<Products> purchaseHistory(int userId,UserRoles roles)
    {
        Users user = userService.getUserById(userId);
        if(user == null)
        {
            throw new RuntimeException("user not found");
        }//PurchaseHistory

        if (!user.getRoles().equals(roles.User) || !roles.equals(roles.User)) {

            throw new RuntimeException("Admins cannot buy products");
        }

        List<Products> productsList = PurchaseHistory.get(userId);
        if(productsList == null)
        {
            throw new RuntimeException("You have not purchase yet");
        }
        return productsList;

    }


    //getProductsFromCart
    public List<Products> getAllProductsFromCart(int userId,UserRoles roles)
    {


        Users user = userService.getUserById(userId);
        if(!user.getRoles().equals(roles.User) || !roles.equals(UserRoles.User))
        {
            throw new RuntimeException("Admin can't access to the endpoint");
        }
        if(user != null)
        {
            List<Products> ProductCartList = Orders.get(userId);
            return ProductCartList;
        }
     throw new RuntimeException("You have not added product yet");
    }



}
