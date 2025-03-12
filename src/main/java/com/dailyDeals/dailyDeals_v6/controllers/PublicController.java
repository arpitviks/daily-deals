package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.LoggingAspect.CustomLogger;
import com.dailyDeals.dailyDeals_v6.controllers.interfaces.PublicControllerInterface;
import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.dto.ApiError;
import com.dailyDeals.dailyDeals_v6.dto.AuthResponse;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.services.ProductService;
import com.dailyDeals.dailyDeals_v6.services.UserService;
import com.dailyDeals.dailyDeals_v6.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController implements PublicControllerInterface {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create-user")
    @Override
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            CustomLogger.setDebugMessage("this is it");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST, "Issue with the request body", new Date()));
        }
        User newUser = userService.saveUser(user);
        //CustomLogger.set("The new Logging message", String.valueOf(LoggingLevels.info));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            AuthResponse authResponse = new AuthResponse(jwt);
            return ResponseEntity.status(HttpStatus.OK).body(jwt);
            //return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            CustomLogger.setErrorMessage("Exception occurred while createAuthenticationToken",e);
            throw new CustomGlobalException(e.getMessage(), true, false);
        }
    }


}
