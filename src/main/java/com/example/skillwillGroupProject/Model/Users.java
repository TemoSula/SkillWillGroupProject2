package com.example.skillwillGroupProject.Model;

import com.example.skillwillGroupProject.Enums.UserRoles;

import java.util.Objects;

public class Users {

    //need Long
    private int id;
    private String username;
    private double Amount;
    private UserRoles roles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", Amount=" + Amount +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Double.compare(users.Amount, Amount) == 0 && username.equals(users.username) && roles == users.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username,  Amount, roles);
    }
}
