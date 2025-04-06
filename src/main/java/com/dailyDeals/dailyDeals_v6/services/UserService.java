package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.UserRepo;
import com.dailyDeals.dailyDeals_v6.services.interfaces.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public UserEntity saveUser(UserEntity user) throws CustomGlobalException{
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public List<UserEntity> saveUsers(List<UserEntity> user) throws CustomGlobalException {
        try {
            return userRepo.saveAll(user);
        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public List<UserEntity> getUsers() throws CustomGlobalException{
        try {
            return userRepo.findAll();
        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public UserEntity getUser(int id) throws CustomGlobalException{
        try {
            UserEntity existingUser = userRepo.findById(id).orElse(null);
            if (existingUser != null) {
                return existingUser;
            } else throw new CustomGlobalException("User is not available", true, false);
        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public UserEntity getUser(String username) throws CustomGlobalException{
        try {
            UserEntity existingUser = userRepo.findByUsername(username).orElse(null);
            if (existingUser != null) {
                return existingUser;
            } else throw new CustomGlobalException("User is not available", true, false);
        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public Object deleteUser(int id) throws CustomGlobalException{
        try {
            if (userRepo.findById(id).orElse(null) != null) {
                userRepo.deleteById(id);
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectNode jsonObject = objectMapper.createObjectNode();
                jsonObject.put("userId", id);
                jsonObject.put("message", "User deleted successfully");
                return jsonObject;
            } else throw new CustomGlobalException("User is not available", true, false);

        } catch (Exception ex) {
            throw new CustomGlobalException(ex.getMessage(), false, true);
        }
    }

    @Override
    public UserEntity updateUser(UserEntity user) throws CustomGlobalException{
        UserEntity existingUser = getUser(user.getId());
        if (existingUser != null) {
            existingUser.setUserRole(user.getUserRole());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(existingUser);
        }
        throw new CustomGlobalException("User is not available", true, false);
    }

}
