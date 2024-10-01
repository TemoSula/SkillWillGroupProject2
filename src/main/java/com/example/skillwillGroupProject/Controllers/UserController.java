package com.example.skillwillGroupProject.Controllers;

import com.example.skillwillGroupProject.Model.Users;
import com.example.skillwillGroupProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/GetUsers")
    public Collection<Users> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping("/CreateUser")
    public String addUser(@RequestBody Users users)
    {
        return userService.AddUser(users);

    }

    @GetMapping("/getbyId")
    public Users getById(@RequestParam int userId)
    {
     return userService.getUserById(userId);
    }



}
