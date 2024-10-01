package com.example.skillwillGroupProject.Services;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.skillwillGroupProject.Enums.UserRoles;
import com.example.skillwillGroupProject.Model.Users;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<Integer,Users> UserCollection = new HashMap();



    public List<Users> getAllUsers()
    {
        Collection<Users> users = UserCollection.values();
        List<Users> userList = new ArrayList<>();
        for(Users users2 : users )
        {
            userList.add(users2);
        }

        return userList;
    }


    public String AddUser(Users user)
    {
        Users userList = UserCollection.get(user.getId());
        if(userList != null)
        {
            throw new RuntimeException("User already exsist");
        }

        if(user.getRoles().equals(UserRoles.Admin))
        {
            user.setAmount(0);
        }

        UserCollection.put(user.getId(),user);
        return "User Created Successfully";
    }

    public Users getUserById(int id)
    {
        return UserCollection.get(id);
    }


}
