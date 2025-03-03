package com.dailyDeals.dailyDeals_v6.repositories;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.enums.OrderStatus;
import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class OrderCustomRepo implements com.dailyDeals.dailyDeals_v6.repositories.interfaces.OrderCustomRepoInterface {
    @Autowired
    OrderRepo orderRepo;
    @Override
    public CustomOrder PurchaseOrder(Deal deal, User user) throws CustomGlobalException {
        if(deal != null && user != null){
            CustomOrder order = new CustomOrder();
            order.setDeal(deal);
            order.setUser(user);
            order.setOrderStatus(OrderStatus.Active);
            order.setPurchaseDate(LocalDate.now());
            return orderRepo.save(order);
        }else throw new CustomGlobalException("There is an issue with a deal or an user",true,false);
    }
    @Override
    public List<CustomOrder> getAllOrder() throws CustomGlobalException {
        List<CustomOrder> orders = orderRepo.findAll();
        if(!orders.isEmpty()){
            return orders;
        }else throw new CustomGlobalException("Order not found",false,true);
    }
    @Override
    public CustomOrder getOrder(int orderId) throws CustomGlobalException {
        CustomOrder order = orderRepo.findById(orderId).orElse(null);
        if (order != null){
            return order;
        }else throw new CustomGlobalException("Order not found",false,true);
    }
    @Override
    public List<CustomOrder> getCustomerOrders(User user) throws CustomGlobalException {
        List<CustomOrder> orders = orderRepo.findAll();
        if(!orders.isEmpty() && user != null ) {
            List<CustomOrder> customerOrders = orders.stream()
                    .filter(order -> order.getUser().getId() == user.getId())
                    .collect(Collectors.toList());
            if(!customerOrders.isEmpty()){
                return customerOrders;
            }else throw new CustomGlobalException("Order not found for a customer"+ user.getUsername(),false,true);

        }else throw new CustomGlobalException("Order not found",false,true);
    }
    @Override
    public CustomOrder cancelOrder(int orderId) throws CustomGlobalException {
        CustomOrder order = this.getOrder(orderId);
        order.setOrderStatus(OrderStatus.Canceled);
        return orderRepo.save(order);
    }
    @Override
    public CustomOrder updateOrder(int orderId, CustomOrder updatedOrder) throws CustomGlobalException, IllegalAccessException {
        CustomOrder existingOrder = this.getOrder(orderId);
        Field[] fields = existingOrder.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object newValue = field.get(updatedOrder);
            Object oldValue = field.get(existingOrder);
            if (newValue != null && !String.valueOf(field.getName()).equals("id") && !newValue.equals(oldValue)) {
                field.set(existingOrder, newValue);
            }
        }
        return orderRepo.save(existingOrder);
    }
    @Override
    public Boolean checkDealPurchasedByUser(User user, Deal deal) throws CustomGlobalException {
        List<CustomOrder> foundOrder = orderRepo.findAll().stream().
                filter(currOrder -> currOrder.getUser().getId() == user.getId() &&
                        currOrder.getDeal().getDealId() == deal.getDealId())
                .collect(Collectors.toList());
        if(foundOrder.isEmpty()){
            return  true;
        } else throw new CustomGlobalException("Order is already placed for a user",true,false);

    }
    @Override
    public boolean checkOrderOwner(CustomOrder order, User owner){
        if(Objects.equals(owner.getUserRole(), UserRole.Admin.toString())){
            return true;
        }else return order.getUser().getId() == owner.getId();
    }
}
