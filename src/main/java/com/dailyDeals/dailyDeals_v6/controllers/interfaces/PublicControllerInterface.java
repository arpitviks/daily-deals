package com.dailyDeals.dailyDeals_v6.controllers.interfaces;

import com.dailyDeals.dailyDeals_v6.models.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PublicControllerInterface {
    @PostMapping("/create-user")
    ResponseEntity<Object> addUser(@Valid @RequestBody User user, BindingResult result);

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody User user);
}
