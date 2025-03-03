package com.dailyDeals.dailyDeals_v6.controllers.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderControllerInterface {
    @PostMapping("/{dealId}")
    ResponseEntity<Object> purchaseAnOrder(@PathVariable int dealId) throws CustomGlobalException;

    @GetMapping("/customerOrders")
    ResponseEntity<Object> getCustomerOrder() throws CustomGlobalException;

    @GetMapping("/")
    ResponseEntity<Object> getAllOrder() throws CustomGlobalException;

    @DeleteMapping("/{orderId}")
    ResponseEntity<Object> cancelOrder(@PathVariable int orderId) throws CustomGlobalException;

    @GetMapping("/{orderId}")
    ResponseEntity<Object> getOrder(@PathVariable int orderId) throws CustomGlobalException;

    @PatchMapping("/{orderId}/{userId}")
    ResponseEntity<Object> updateOrder(@PathVariable int orderId,
                                       @RequestBody CustomOrder order) throws CustomGlobalException, IllegalAccessException;
}
