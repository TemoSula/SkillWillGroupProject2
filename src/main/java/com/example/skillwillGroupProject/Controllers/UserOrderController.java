package com.example.skillwillGroupProject.Controllers;

import com.example.skillwillGroupProject.Enums.UserRoles;
import com.example.skillwillGroupProject.Model.Products;
import com.example.skillwillGroupProject.Model.UserOrder;
import com.example.skillwillGroupProject.Services.UserOrderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserOrderController {

    @Autowired
    UserOrderService orderService;

    @PostMapping("/AddInCart")
    public UserOrder AddIncart(@RequestParam int userId, @RequestParam int productId, @RequestParam UserRoles roles)
    {
        orderService.AddInCart(userId,productId,roles);
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setProductId(productId);
        return userOrder;
    }

    @GetMapping("/GetMyCart")
    public List<Products> GetCarts(@RequestParam int userId,@RequestParam UserRoles roles)
    {
        return orderService.getAllProductsFromCart(userId,roles);
    }

    @PostMapping("/BuyProduct")
    public String buyProduct(@RequestParam int userId, @RequestParam int productId, @RequestParam UserRoles roles)
    {
        orderService.BuyProduct(userId,productId,roles);
        return "Successfully Purchased";
    }

    @GetMapping("/purchaseHistory")
    public List<Products> PurchaseHistory (@RequestParam int userId,@RequestParam UserRoles roles)
    {
        return orderService.purchaseHistory(userId,roles);
    }

    @DeleteMapping("/RemoveItemFromCart")
    public void RemoveItemFromCart (@RequestParam int userId,@RequestParam int productId,@RequestParam UserRoles roles)
    {
        orderService.RemoveFromCart(userId,productId,roles);
    }



}
