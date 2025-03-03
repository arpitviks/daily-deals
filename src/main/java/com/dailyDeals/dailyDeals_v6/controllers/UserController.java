package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.LoggingAspect.CustomLogger;
import com.dailyDeals.dailyDeals_v6.controllers.interfaces.UserControllerInterface;
import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.dto.ApiError;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUsers(@PathVariable int userId) {
        User users = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @DeleteMapping("/{userIdToDelete}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userIdToDelete) {
            Object obj = userService.deleteUser(userIdToDelete);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PatchMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

}
