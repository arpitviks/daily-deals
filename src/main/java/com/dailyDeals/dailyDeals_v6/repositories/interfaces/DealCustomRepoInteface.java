package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.models.User;

import java.util.List;

public interface DealCustomRepoInteface {
    Deal addDeal(Deal deal, User user);

    List<Deal> getAllDeals();

    Deal getDeal(int dealId);

    Object deleteDeal(int dealId);

    Deal updateDeal(Deal deal, Product product) throws IllegalAccessException;

    Deal increaseDealQuantity(int dealId);

    Deal decreaseDealQuantity(int dealId);
}
