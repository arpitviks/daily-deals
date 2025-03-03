package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.User;

import java.util.List;

public interface OrderServiceInterface {
    CustomOrder purchaseOrder(int dealId, String userName) throws CustomGlobalException;

    List<CustomOrder> getAllOrder() throws CustomGlobalException;

    List<CustomOrder> getCustomerOrder(String userName) throws CustomGlobalException;

    CustomOrder getOrder(int orderId) throws CustomGlobalException;

    CustomOrder cancelOrder(int orderId, String userName) throws CustomGlobalException;

    CustomOrder updateOrder(int orderId, CustomOrder updatedOrder, String userName) throws CustomGlobalException, IllegalAccessException;

    //check user has already purchased the deal or not
    Boolean checkDealPurchasedByUser(User user, Deal deal) throws CustomGlobalException;
}
