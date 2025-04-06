package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;

import java.util.List;

public interface UserServiceInterface {
    public UserEntity saveUser(UserEntity user);
    public List<UserEntity> saveUsers(List<UserEntity> user);
    public List<UserEntity> getUsers();
    public UserEntity getUser(int id);

    public UserEntity getUser(String username) throws CustomGlobalException;

    public Object deleteUser(int id);
    public UserEntity updateUser(UserEntity user);
}
