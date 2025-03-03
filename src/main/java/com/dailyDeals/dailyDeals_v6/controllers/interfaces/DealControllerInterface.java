package com.dailyDeals.dailyDeals_v6.controllers.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface DealControllerInterface {
    @PostMapping("/")
    ResponseEntity<Object> addDeal(@Valid @RequestBody Deal deal, BindingResult result) throws CustomGlobalException;

    @GetMapping("/")
    ResponseEntity<Object> getAllDeals() throws CustomGlobalException;

    @DeleteMapping("/{dealId}")
    ResponseEntity<Object> deleteDeal(@PathVariable int dealId) throws CustomGlobalException;

    @PatchMapping("/")
    ResponseEntity<Object> updateDeal(@RequestBody Deal dealToUpdate) throws CustomGlobalException, IllegalAccessException;

    @GetMapping("/{dealId}")
    ResponseEntity<Object> getDeal(@PathVariable int dealId) throws CustomGlobalException;

    @PatchMapping("/increaseQuantity/{dealId}")
    ResponseEntity<Object> increaseQuantity(@PathVariable int dealId) throws CustomGlobalException;

    @PatchMapping("/decreaseQuantity/{dealId}")
    ResponseEntity<Object> decreaseQuantity(@PathVariable int dealId) throws CustomGlobalException;
}
