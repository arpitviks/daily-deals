package com.dailyDeals.dailyDeals_v6.models;

import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Order order) {
//        this.orders.add(order);
//    }
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private int id;
    @NotNull
    @Column(name = "userRole", nullable = false)
    private String userRole;
    @NotEmpty
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //private List<String> order = new ArrayList<String>();
}
