package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.controllers.interfaces.UserControllerInterface;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;
import com.dailyDeals.dailyDeals_v6.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUsers(@PathVariable int userId) {
        UserEntity users = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getUsers() {
        List<UserEntity> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @DeleteMapping("/{userIdToDelete}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userIdToDelete) {
            Object obj = userService.deleteUser(userIdToDelete);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PatchMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody UserEntity user) {
            UserEntity updatedUser = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

}
