package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.controllers.interfaces.OrderControllerInterface;
import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderControllerInterface {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{dealId}")
    @Override
    public ResponseEntity<Object> purchaseAnOrder(@PathVariable int dealId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        CustomOrder order = orderService.purchaseOrder(dealId,userName);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/customerOrders")
    @Override
    public ResponseEntity<Object> getCustomerOrder() throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<CustomOrder> orders = orderService.getCustomerOrder(userName);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    @GetMapping("/")
    @Override
    public ResponseEntity<Object> getAllOrder() throws CustomGlobalException {
        List<CustomOrder> orders = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @DeleteMapping("/{orderId}")
    @Override
    public ResponseEntity<Object> cancelOrder(@PathVariable int orderId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Object order = orderService.cancelOrder(orderId, userName);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
    @GetMapping("/{orderId}")
    @Override
    public ResponseEntity<Object> getOrder(@PathVariable int orderId) throws CustomGlobalException {
        CustomOrder order = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
    @PatchMapping("/{orderId}/{userId}")
    @Override
    public ResponseEntity<Object> updateOrder(@PathVariable int orderId,
                                              @RequestBody CustomOrder order) throws  CustomGlobalException, IllegalAccessException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        CustomOrder updatedOrder = orderService.updateOrder(orderId,order,userName);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }
}
