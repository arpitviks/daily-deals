package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.models.DealEntity;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;

import java.util.List;

public interface OrderCustomRepoInterface {
    CustomOrder PurchaseOrder(DealEntity deal, UserEntity user) throws CustomGlobalException;

    List<CustomOrder> getAllOrder() throws CustomGlobalException;

    CustomOrder getOrder(int orderId) throws CustomGlobalException;

    List<CustomOrder> getCustomerOrders(UserEntity user) throws CustomGlobalException;

    CustomOrder cancelOrder(int orderId) throws CustomGlobalException;

    CustomOrder updateOrder(int orderId, CustomOrder updatedOrder) throws CustomGlobalException, IllegalAccessException;

    Boolean checkDealPurchasedByUser(UserEntity user, DealEntity deal) throws CustomGlobalException;

    boolean checkOrderOwner(CustomOrder order, UserEntity owner);
}
