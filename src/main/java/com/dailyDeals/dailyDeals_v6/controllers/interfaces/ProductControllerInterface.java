package com.dailyDeals.dailyDeals_v6.controllers.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ProductControllerInterface {
    @PostMapping("/")
    ResponseEntity<Object> addProduct(@Valid @RequestBody Product product, BindingResult result) throws CustomGlobalException;

    @GetMapping("/")
    ResponseEntity<Object> getAllProducts() throws CustomGlobalException;

    @DeleteMapping("/{productId}")
    ResponseEntity<Object> deleteProduct(@PathVariable int productId) throws CustomGlobalException;

    @GetMapping("/{productId}")
    ResponseEntity<Object> getProduct(@PathVariable int productId) throws CustomGlobalException;

    @PatchMapping("/")
    ResponseEntity<Object> updateProduct(@RequestBody Product productToUpdate) throws CustomGlobalException, IllegalAccessException;
}
