package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.User;

import java.util.List;

public interface UserServiceInterface {
    public User saveUser(User user);
    public List<User> saveUsers(List<User> user);
    public List<User> getUsers();
    public User getUser(int id);

    public User getUser(String username) throws CustomGlobalException;

    public Object deleteUser(int id);
    public User updateUser(User user);
}
