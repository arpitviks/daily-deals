package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.User;

import java.util.List;

public interface OrderCustomRepoInterface {
    CustomOrder PurchaseOrder(Deal deal, User user) throws CustomGlobalException;

    List<CustomOrder> getAllOrder() throws CustomGlobalException;

    CustomOrder getOrder(int orderId) throws CustomGlobalException;

    List<CustomOrder> getCustomerOrders(User user) throws CustomGlobalException;

    CustomOrder cancelOrder(int orderId) throws CustomGlobalException;

    CustomOrder updateOrder(int orderId, CustomOrder updatedOrder) throws CustomGlobalException, IllegalAccessException;

    Boolean checkDealPurchasedByUser(User user, Deal deal) throws CustomGlobalException;

    boolean checkOrderOwner(CustomOrder order, User owner);
}
