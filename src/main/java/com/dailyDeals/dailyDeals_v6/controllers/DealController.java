package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.controllers.interfaces.DealControllerInterface;
import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.dto.ApiError;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.services.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealController implements DealControllerInterface {
    @Autowired
    DealService dealService;

    @PostMapping("/")
    @Override
    public ResponseEntity<Object> addDeal(@Valid @RequestBody Deal deal, BindingResult result) throws CustomGlobalException {
        if (result.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError( HttpStatus.BAD_REQUEST,(result.getAllErrors()).toString(),new Date()));
            //return result.getAllErrors().toString();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Deal dealToCreate = dealService.addDeal(deal,userName);
        return ResponseEntity.status(HttpStatus.OK).body(dealToCreate);
    }
    @GetMapping("/")
    @Override
    public ResponseEntity<Object> getAllDeals() throws CustomGlobalException {
        List<Deal> dealList = dealService.getAllDeals();
        return ResponseEntity.status(HttpStatus.OK).body(dealList);
    }
    @DeleteMapping("/{dealId}")
    @Override
    public ResponseEntity<Object> deleteDeal(@PathVariable int dealId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Object object = dealService.deleteDeal(dealId,userName);
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }
    @PatchMapping("/")
    @Override
    public ResponseEntity<Object> updateDeal(@RequestBody Deal dealToUpdate) throws CustomGlobalException, IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Deal updatedDeal = dealService.updateDeal(dealToUpdate,userName);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDeal);
    }
    @GetMapping("/{dealId}")
    @Override
    public ResponseEntity<Object> getDeal(@PathVariable int dealId) throws CustomGlobalException {
        Deal deal = dealService.getDeal(dealId);
        return ResponseEntity.status(HttpStatus.OK).body(deal);
    }
    @PatchMapping("/increaseQuantity/{dealId}")
    @Override
    public ResponseEntity<Object> increaseQuantity(@PathVariable int dealId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Deal deal = dealService.increaseDealQuantityForAdmins(dealId,userName);
        return ResponseEntity.status(HttpStatus.OK).body(deal);
    }
    @PatchMapping("/decreaseQuantity/{dealId}")
    @Override
    public ResponseEntity<Object> decreaseQuantity(@PathVariable int dealId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Deal deal = dealService.decreaseDealQuantityForAdmins(dealId,userName);
        return ResponseEntity.status(HttpStatus.OK).body(deal);
    }
}
